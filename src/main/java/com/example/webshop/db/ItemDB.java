package com.example.webshop.db;

import com.example.webshop.bo.Item;

import java.sql.*;
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
                        rs.getString("category"),
                        rs.getString("status")
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
                item.getDescription(), item.getQuantity(), item.getCategory(),item.getStatus());
        try{
            System.out.println("DB item: "+ itemDB.toString());
            Connection con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO T_Item (name, price, description, quantity, category,status) VALUES " +
                            "(?,?,?,?,?,?)");
            ps.setString(1,itemDB.getName());
            ps.setInt(2,itemDB.getPrice());
            ps.setString(3,itemDB.getDescription());
            ps.setInt(4,itemDB.getQuantity());
            ps.setString(5,itemDB.getCategory());
            ps.setString(6,itemDB.getStatus());
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
                        rs.getString("category"),
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
                        rs.getString("category"),
                        (String) rs.getObject("status")
                ));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    protected ItemDB(int id, String name, int price, String description, int quantity, String category, String status) {
        super(id, name, price, description, quantity, category, status);
    }
}