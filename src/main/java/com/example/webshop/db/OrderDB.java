package com.example.webshop.db;

import com.example.webshop.bo.CartItem;
import com.example.webshop.bo.Item;
import com.example.webshop.bo.Order;
import com.example.webshop.bo.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class OrderDB extends Order {

    public OrderDB(int orderID, int userID, Date date, String status) {
        super(orderID, userID, date, status);
    }


    public static Collection<OrderDB> getAllOrders(){
        ResultSet rs;
        ArrayList<OrderDB> orders = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * from T_Order");

            while(rs.next()){
                orders.add(new
                        OrderDB(
                        rs.getInt("orderID"),
                        rs.getInt("userID"),
                        rs.getDate("date"),
                        rs.getString("status")
                ));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    public static boolean placeOrder(ArrayList<CartItem<Item>> cartList, User user) throws SQLException {
        if(cartList == null) return false;
        Connection con = null;

        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);

            Item item;
            for(CartItem<Item> cartItem : cartList){
                item = ItemDB.getItemIdByName(cartItem.getItem().getName());
                int difference = item.getQuantity() - cartItem.getQuantity();
                if(difference < 0) throw new SQLException("NOT ENOUGH ITEM IN STOCK");
                if(difference==0) cartItem.getItem().setStatus("OUT_OF_STOCK");
                item.setQuantity(difference);
            }

            PreparedStatement psT_Order = con.prepareStatement("INSERT INTO T_Order (userID, date, status) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            psT_Order.setInt(1, user.getId());
            psT_Order.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
            psT_Order.setString(3, "active");


            int affectedRows = psT_Order.executeUpdate();
            int newOrderId = 0;
            ResultSet generatedKeys = psT_Order.getGeneratedKeys();
            if (generatedKeys.next()) {
                newOrderId = generatedKeys.getInt(1); // or generatedKeys.getInt("orderID");
            }

            PreparedStatement psItem = con.prepareStatement("UPDATE T_Item SET quantity = ?, status  = ? WHERE itemID = ?");
            for(CartItem<Item> i : cartList){
                psItem.setInt(1,i.getQuantity());
                psItem.setString(2,i.getItem().getStatus());
                psItem.setInt(3, i.getItem().getId());
                psItem.addBatch();
            }
            psItem.executeBatch();

            PreparedStatement psT_PurchaseItems = con.prepareStatement("INSERT INTO T_PurchaseItems (orderID, itemID, quantity) VALUES (?, ?, ?)");
            for(CartItem<Item> cartItem : cartList) {
                psT_PurchaseItems.setInt(1, newOrderId);
                psT_PurchaseItems.setInt(2, cartItem.getItem().getId());
                psT_PurchaseItems.setInt(3, cartItem.getQuantity());
                psT_PurchaseItems.addBatch();
            }
            psT_PurchaseItems.executeBatch();

            con.commit();

        }
        catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
            return false;
        }
        finally{
            assert con != null;
            con.setAutoCommit(true);

        }
        return true;
    }
}
