package com.example.webshop.db;

import com.example.webshop.bo.Item;

import java.sql.*;
import java.util.ArrayList;

/**
 * Manages database operations related to items in the webshop system.
 * Extends the Item class to provide additional database functionalities.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class ItemDB extends Item {

    /**
     * Marks an item as inactive in the database.
     *
     * @param item Item object to be marked as inactive.
     * @return boolean True if the operation is successful, otherwise false.
     */
    public static boolean removeItem(Item item) {
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE T_Item SET active = 0 WHERE itemID = ?"); {
            ps.setInt(1, item.getId());

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
             }

        } catch (SQLException e) {
            System.out.println("Error while marking item as inactive: " + e.getMessage());
            return false;
        }
    }

    /**
     * Fetches all active items from the database.
     *
     * @return ArrayList<Item> List of active Item objects.
     */
    public static ArrayList<Item> searchItems(){
        ResultSet rs;
        ArrayList<Item> items = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM T_Item WHERE active = 1");

            while(rs.next()){
                items.add(new
                        ItemDB(rs.getInt("itemID"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getBoolean("active")
                        ));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Updates an existing item's details in the database.
     *
     * @param item Item object containing the updated details.
     * @return boolean True if the update is successful, otherwise false.
     */
    public static boolean editItem(Item item){
        Item itemDB = new ItemDB(item.getId(), item.getName(), item.getPrice(), item.getDescription(), item.getQuantity(), item.getCategory(),item.getStatus(), item.isActive());
        try{
            Connection con = DBManager.getConnection();

            PreparedStatement ps = con.prepareStatement("UPDATE T_Item SET name = ?, price = ?, description = ?, quantity = ?, category = ?,status = ? WHERE itemID = ?");
            ps.setString(1,itemDB.getName());
            ps.setInt(2,itemDB.getPrice());
            ps.setString(3,itemDB.getDescription());
            ps.setInt(4,itemDB.getQuantity());
            ps.setString(5,itemDB.getCategory());
            ps.setString(6,itemDB.getStatus());
            ps.setInt(7,item.getId());
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Inserts a new item into the database.
     *
     * @param item Item object to be inserted.
     * @return boolean True if the insertion is successful, otherwise false.
     * @throws SQLException If a database error occurs.
     */
    public static boolean createItem(Item item) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DBManager.getConnection();
            if(con == null) throw new SQLException("No connection");

            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO T_Item (name, price, description, quantity, category,status) VALUES (?,?,?,?,?,?)");
            ps.setString(1,item.getName());
            ps.setInt(2,item.getPrice());
            ps.setString(3,item.getDescription());
            ps.setInt(4,item.getQuantity());
            ps.setString(5,item.getCategory());
            ps.setString(6,item.getStatus());
            ps.executeUpdate();

            con.commit();

        }
        catch (SQLException e){
            con.rollback();
            e.printStackTrace();
            return false;
        }
        finally {
            con.setAutoCommit(true);
        }
        return true;
    }

    /**
     * Retrieves an item by its name from the database.
     *
     * @param itemName Name of the item to be retrieved.
     * @return Item The Item object or null if not found.
     */
    public static Item getItemByName(String itemName){
        ResultSet rs;
        Connection con;
        PreparedStatement ps;
        Item item = null;
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement("SELECT * from T_Item WHERE T_Item.name = ?");
            ps.setString(1,itemName);
            rs = ps.executeQuery();

            while(rs.next()) {
                item = new ItemDB(
                        rs.getInt("itemID"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getBoolean("active"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Retrieves items associated with a given order ID.
     *
     * @param orderID The ID of the order.
     * @return ArrayList<ItemDB> List of ItemDB objects associated with the order.
     */
    public static ArrayList<ItemDB> getItemsIdByOrderID(int orderID){
        ResultSet rs;
        ArrayList<ItemDB> items = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT T_Item.* FROM T_PurchaseItems INNER JOIN T_Item ON T_PurchaseItems.itemID = T_Item.itemID WHERE T_PurchaseItems.orderID = ?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, orderID); // Set the parameter for the prepared statement
            rs = pst.executeQuery();

            while(rs.next()){
                items.add(new
                        ItemDB(rs.getInt("itemID"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getBoolean("active")
                ));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    protected ItemDB(int id, String name, int price, String description, int quantity, String category, String status, boolean active) {
        super(id, name, price, description, quantity, category, status, active);
    }
}