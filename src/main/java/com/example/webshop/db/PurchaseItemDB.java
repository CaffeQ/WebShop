package com.example.webshop.db;

import com.example.webshop.bo.CartItem;
import com.example.webshop.bo.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            Statement st = con.createStatement();
            String query = "SELECT T_Item.*, T_PurchaseItems.quantity AS purchase_quantity FROM T_PurchaseItems INNER JOIN T_Item ON T_PurchaseItems.itemID = T_Item.itemID WHERE T_PurchaseItems.orderID = " + orderID;

            rs = st.executeQuery(query);
            while(rs.next()){
                cartItems.add(new PurchaseItemDB(
                        new ItemDB(
                            rs.getInt("itemID"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getString("description"),
                            rs.getInt("quantity"),
                            rs.getString("category"),
                            rs.getString("status")
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
