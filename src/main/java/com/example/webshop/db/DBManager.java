package com.example.webshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager{
    private static Connection con;
    public static Connection getConnection(){
        if(con != null)
            return con;
        Connection newCon = null;
        try {
             newCon = DriverManager.getConnection("jdbc:mysql://localhost" +
                    "user=root&password=123");
        } catch (SQLException e){
            System.out.println("Could not connect to SQL server");
            e.printStackTrace();
        }
        con = newCon;
        return con;
    }

}