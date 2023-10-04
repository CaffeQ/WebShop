package com.example.webshop.db;

import com.example.webshop.bo.Item;

import java.sql.*;
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

    public static boolean createItem(Item item){
        ItemDB itemDB = new ItemDB(item.getId(), item.getName(), item.getPrice(),
                item.getDescription(), item.getQuantity(), item.getStatus());
        try{
            System.out.println("DB item: "+ itemDB.toString());
            Connection con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO T_Item (name, price, description, quantity, status) VALUES " +
                            "(?,?,?,?,?)");
            ps.setString(1,itemDB.getName());
            ps.setInt(2,itemDB.getPrice());
            ps.setString(3,itemDB.getDescription());
            ps.setInt(4,itemDB.getQuantity());;
            ps.setString(5,itemDB.getStatus());
            int rowsAffected = ps.executeUpdate();
            System.out.println("Rows affected "+rowsAffected);
            if (rowsAffected > 0) {
                System.out.println("Item added successfully");
                con.commit();
                return true;
            } else {
                System.out.println("Failed to add item");
                con.rollback();
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    protected ItemDB(int id, String name, int price, String description, int quantity, String status) {
        super(id, name, price, description, quantity, status);
    }
}