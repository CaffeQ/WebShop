package com.example.webshop.db;

import com.example.webshop.bo.User;

import java.sql.*;


public class UserDB extends User {


    public static UserDB searchUser(String userName){
        ResultSet rs = null;
        UserDB userDB = null;
        try{
            Connection con = DBManager.getConnection();
            System.out.println("UserDB name = "+userName);
            PreparedStatement ps = con.prepareStatement("SELECT * from T_User where T_User.name = "+ "'"+ userName +"'");
            ps.executeQuery();

            if (rs.next()){
                userDB = new UserDB(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("token"));
            }
        }catch (SQLException e){
            System.out.println("User not found");
            e.printStackTrace();
        }
        return userDB;
    }

    public UserDB(int id, String name, String password, String role, String address) {
        super(id, name, password, role, address);
    }
}
