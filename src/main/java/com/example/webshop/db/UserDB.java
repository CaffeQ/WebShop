package com.example.webshop.db;

import com.example.webshop.bo.User;

import java.sql.*;


public class UserDB extends User {


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

    public UserDB(int id, String email, String password, String role, String address) {
        super(id, email, password, role, address);
    }


}
