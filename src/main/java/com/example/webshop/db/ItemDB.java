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

    /*
    public Collection getLocalItems(){

        ArrayList<ItemDB> items = new ArrayList<>();

        //items.add(new ItemDB(1,"Mug","Blue coffe mug"));
        //items.add(new ItemDB(2,"Cup","Red cup"));

        return items;
    }

     */
    public static Collection searchItems(){
        ResultSet rs = null;
        ArrayList<ItemDB> items = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * from T_Item");
/*
Error Code: 1064. You have an error in your SQL syntax;
check the manual that corresponds to your MySQL server version for the
right syntax to use near 'T_Item' at line 1

 */
            while(rs.next()){
                items.add(new
                        ItemDB(rs.getInt("itemID"),
                        rs.getString("name"),
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
    protected ItemDB(int id, String name, String description, int quantity, String status) {
        super(id, name, description, quantity, status);
    }
}