package com.example.webshop.db;

import com.example.webshop.bo.CartItem;

import java.sql.*;
import java.util.ArrayList;

public class PurchaseItemDB extends CartItem{

    protected PurchaseItemDB(ItemDB item, int quantity) {
        super(item, quantity);
    }

    public static ArrayList<CartItem> getCartItemByOrderID(int orderID){
        ResultSet rs;
        ArrayList<CartItem> cartItems = new ArrayList<>();

        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT T_Item.*, T_PurchaseItems.quantity AS purchase_quantity FROM T_PurchaseItems INNER JOIN T_Item ON T_PurchaseItems.itemID = T_Item.itemID WHERE T_PurchaseItems.orderID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderID);

            rs = pst.executeQuery();
            while(rs.next()){
                cartItems.add(new PurchaseItemDB(
                        new ItemDB(
                            rs.getInt("itemID"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getString("description"),
                            rs.getInt("quantity"),
                            rs.getString("category"),
                            rs.getString("status"),
                            rs.getBoolean("active")
                        ),
                            rs.getInt("purchase_quantity")
                ));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return cartItems;
    }

}
