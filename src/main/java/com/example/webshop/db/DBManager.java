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

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/webshop";

            newCon = DriverManager.getConnection(url, "root", "password123"); // <-- Change login here

        }
        catch (SQLException e) {
            System.out.println("Could not connect to SQL server");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        con = newCon;
        return con;
    }

}