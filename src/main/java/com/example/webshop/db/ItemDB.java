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

    public Collection getLocalItems(){
        ArrayList<ItemDB> items = new ArrayList<>();

        items.add(new ItemDB(1,"Mug","Blue coffe mug"));
        items.add(new ItemDB(2,"Cup","Red cup"));

        return items;
    }
    public Collection searchItems(){
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, name, description from T_Item");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    protected ItemDB(int id, String name, String desc) {
        super(id, name, desc);
    }
}