package com.example.webshop.db;

import com.example.webshop.bo.User;

import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Represents a user in the webshop database, extends the User class.
 * Provides a static method for searching a user by their username in the database.
 */
public class UserDB extends User {

    /**
     * Searches for a user in the database by username.
     *
     * @param email The username to search for.
     * @return A UserDB object if the user exists, otherwise null.
     */
    public static User searchUser(String email){
        ResultSet rs;
        UserDB userDB = null;
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * from T_User where T_User.email = ?");
            ps.setString(1,email);
            rs = ps.executeQuery();

            if (rs.next()){
                userDB = new UserDB(
                        rs.getInt("userID"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("token"),
                        rs.getBoolean("isActive"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userDB;
    }

    public static ArrayList<User> getUsersByStatus(boolean status){
        ResultSet rs;
        ArrayList<User> users = new ArrayList<>();
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * from T_User WHERE isActive = ?");
            ps.setBoolean(1,status);
            rs = ps.executeQuery();

            while (rs.next()){
                users.add(new UserDB(
                        rs.getInt("userID"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("token"),
                        rs.getBoolean("isActive")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public static boolean removeUserByUserID(int userID){
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE T_USER set isActive = false WHERE userID = ?");
            ps.setInt(1,userID);
            ps.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean changeUserRole(User user){
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE T_USER set role = ? WHERE userID = ?");
            ps.setString(1,user.getRole());
            ps.setInt(2,user.getId());
            ps.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean activateUserByUserID(int userID){
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE T_USER set isActive = true WHERE userID = ?");
            ps.setInt(1,userID);
            ps.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<User> getAllUsers(){
        ResultSet rs;
        ArrayList<User> users = new ArrayList<>();
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * from T_User");
            rs = ps.executeQuery();

            while (rs.next()){
                users.add(new UserDB(
                        rs.getInt("userID"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("token"),
                        rs.getBoolean("isActive")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Constructs a new UserDB object.
     *
     * @param id The user ID.
     * @param email The user's email.
     * @param password The user's password.
     * @param role The role of the user.
     * @param address The address of the user.
     */
    public UserDB(int id, String email, String password, String role, String address, boolean isActive) {
        super(id, email, password, role, address, isActive);
    }


}
