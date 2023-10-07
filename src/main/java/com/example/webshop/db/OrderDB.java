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

    public OrderDB(int orderID, int userID, Date date, String status, ArrayList<CartItem> items) {
        super(orderID, userID, date, status, items);
    }
    
    public static OrderDB getOrderByID(int orderID){
        Connection con;
        ResultSet rs;
        OrderDB orderDB = null;
        try{
            con = DBManager.getConnection();
            PreparedStatement psT_Order = con.prepareStatement("SELECT * FROM T_Order  WHERE orderID = " + orderID);
            rs = psT_Order.executeQuery();

            ArrayList<CartItem> items = PurchaseItemDB.getCartItemByOrderID(orderID);
            
            while(rs.next()){
                orderDB = new OrderDB(
                        rs.getInt("orderID"),
                        rs.getInt("userID"),
                        rs.getDate("date"),
                        rs.getString("status"),
                        items
                );
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            return orderDB;
        }
    }

    public static boolean sendOrder(int orderID){
        Connection con;
        try{
            con = DBManager.getConnection();
            PreparedStatement psT_Order = con.prepareStatement("UPDATE T_Order SET status = ? WHERE orderID = " + orderID);
            psT_Order.setString(1, "sent");
            psT_Order.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            return true;
        }
    }


    public static Collection<Order> getAllOrders(){
        ResultSet rs;
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            if(con == null) {
                throw new SQLException("Failed to establish connection.");
            }
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * from T_Order");

            while(rs.next()){
                int orderID = rs.getInt("orderID");
                orders.add(new
                        OrderDB(
                            orderID,
                            rs.getInt("userID"),
                            rs.getDate("date"),
                            rs.getString("status"),
                            PurchaseItemDB.getCartItemByOrderID(orderID)
                ));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }


    public static Collection<Order> getOrderByStatus(String status){
        ResultSet rs;
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            if(con == null) {
                throw new SQLException("Failed to establish connection.");
            }

            String query = "SELECT * from T_Order WHERE status = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, status);

            rs = pst.executeQuery();

            while(rs.next()){
                int orderID = rs.getInt("orderID");
                orders.add(new
                        OrderDB(
                        orderID,
                        rs.getInt("userID"),
                        rs.getDate("date"),
                        rs.getString("status"),
                        PurchaseItemDB.getCartItemByOrderID(orderID)
                ));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    public static boolean placeOrder(Order order, User user) throws SQLException {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);

            if (!updateInventory(order.getItems())) {
                throw new SQLException("NOT ENOUGH ITEM IN STOCK");
            }

            int newOrderId = createOrder(user);

            insertPurchaseDetails(newOrderId, order);

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                con.rollback();
            }
            return false;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
            }
        }
        return true;
    }

    public static boolean updateInventory(ArrayList<CartItem> cartList) {
        try {
            Connection con = DBManager.getConnection();
            Item item;
            PreparedStatement psItem = con.prepareStatement("UPDATE T_Item SET quantity = ?, status  = ? WHERE itemID = ?");

            for (CartItem cartItem : cartList) {
                item = ItemDB.getItemByName(cartItem.getItem().getName());
                int difference = item.getQuantity() - cartItem.getQuantity();
                if (difference < 0) return false;

                if (difference == 0) cartItem.getItem().setStatus("OUT_OF_STOCK");

                cartItem.getItem().setQuantity(difference);
                psItem.setInt(1, cartItem.getItem().getQuantity());
                psItem.setString(2, cartItem.getItem().getStatus());
                psItem.setInt(3, cartItem.getItem().getId());
                psItem.addBatch();
            }
            psItem.executeBatch();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static int createOrder(User user) {
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement psT_Order = con.prepareStatement("INSERT INTO T_Order (userID, date, status) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            psT_Order.setInt(1, user.getId());
            psT_Order.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
            psT_Order.setString(3, "active");
            psT_Order.executeUpdate();

            ResultSet generatedKeys = psT_Order.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void insertPurchaseDetails(int newOrderId, Order order) {
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement psT_PurchaseItems = con.prepareStatement("INSERT INTO T_PurchaseItems (orderID, itemID, quantity) VALUES (?, ?, ?)");

            for (CartItem cartItem : order.getItems()) {
                psT_PurchaseItems.setInt(1, newOrderId);
                psT_PurchaseItems.setInt(2, cartItem.getItem().getId());
                psT_PurchaseItems.setInt(3, cartItem.getQuantity());
                psT_PurchaseItems.addBatch();
            }
            psT_PurchaseItems.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
