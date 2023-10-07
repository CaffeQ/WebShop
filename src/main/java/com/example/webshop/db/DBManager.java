package com.example.webshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages database connections for the webshop system.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class DBManager{
    private static Connection con;

    /**
     * Retrieves a Connection object for interacting with the database.
     * If a connection already exists, the existing connection is returned;
     * otherwise, a new connection is established.
     *
     * @return Connection The current active database connection.
     */
    public static Connection getConnection(){
        if(con != null)
            return con;
        Connection newCon = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/webshop";

            newCon = DriverManager.getConnection(url, "root", System.getenv("DB_PASSWORD")); // <-- Change login here

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