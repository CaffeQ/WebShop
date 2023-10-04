package com.example.webshop.db;

import com.example.webshop.bo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class UserDB extends User {


    public static UserDB searchUser(String userName){
        ResultSet rs = null;
        UserDB userDB = null;
        try{
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            System.out.println("UserDB name = "+userName);
            rs = st.executeQuery("SELECT * from T_User where T_User.name = "+ "'"+ userName +"'");

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
