package com.example.webshop.db;

import com.example.webshop.bo.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class ItemDB extends Item {
    public static Collection<ItemDB> searchItems(){
        ResultSet rs = null;
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

    public static ItemDB searchItem(String itemName){
        ResultSet rs = null;
        ItemDB item = null;
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * from T_Item WHERE T_Item.name = " +"'"+itemName+"'");

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

    protected ItemDB(int id, String name, int price, String description, int quantity, String status) {
        super(id, name, price, description, quantity, status);
    }
}