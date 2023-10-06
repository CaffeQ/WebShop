package com.example.webshop.db;

import com.example.webshop.bo.User;

import java.sql.*;


public class UserDB extends User {


    public static UserDB searchUser(String userName){
        ResultSet rs;
        UserDB userDB = null;
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * from T_User where T_User.name = ?");
            ps.setString(1,userName);
            rs = ps.executeQuery();

            if (rs.next()){
                userDB = new UserDB(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("token"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userDB;
    }

    public UserDB(int id, String name, String password, String role, String address) {
        super(id, name, password, role, address);
    }


}
