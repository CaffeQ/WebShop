package com.example.webshop.db;

import com.example.webshop.bo.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class ItemDB extends Item {
    public static Collection<ItemDB> searchItems(){
        ResultSet rs;
        ArrayList<ItemDB> items = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * from T_Item");

            while(rs.next()){
                items.add(new
                        ItemDB(rs.getInt("itemID"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        (String) rs.getObject("status")
                        ));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    public static ItemDB getItemByName(String itemName){
        ResultSet rs;
        ItemDB item = null;
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * from T_Item WHERE T_Item.name = " +"'" + itemName + "'");

            while(rs.next()) {
                item = new ItemDB(
                        rs.getInt("itemID"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        (String) rs.getObject("status"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    public static ArrayList<ItemDB> getItemsIdByOrderID(int orderID){
        ResultSet rs;
        ArrayList<ItemDB> items = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT T_Item.* FROM T_PurchaseItems INNER JOIN T_Item ON T_PurchaseItems.itemID = T_Item.itemID WHERE T_PurchaseItems.orderID = " + orderID;

            rs = st.executeQuery(query);

            while(rs.next()){
                items.add(new
                        ItemDB(rs.getInt("itemID"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        (String) rs.getObject("status")
                ));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    protected ItemDB(int id, String name, int price, String description, int quantity, String status) {
        super(id, name, price, description, quantity, status);
    }
}