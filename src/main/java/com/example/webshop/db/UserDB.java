package com.example.webshop.db;

import com.example.webshop.bo.User;

import java.sql.*;

/**
 * Represents a user in the webshop database, extends the User class.
 * Provides a static method for searching a user by their username in the database.
 */
public class UserDB extends User {

    /**
     * Searches for a user in the database by username.
     *
     * @param userName The username to search for.
     * @return A UserDB object if the user exists, otherwise null.
     */
    public static UserDB searchUser(String userName){
        ResultSet rs;
        UserDB userDB = null;
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * from T_User where T_User.email = ?");
            ps.setString(1,userName);
            rs = ps.executeQuery();

            if (rs.next()){
                userDB = new UserDB(
                        rs.getInt("userID"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("token"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userDB;
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
    public UserDB(int id, String email, String password, String role, String address) {
        super(id, email, password, role, address);
    }


}
