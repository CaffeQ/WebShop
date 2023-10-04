package com.example.webshop.db;

import com.example.webshop.bo.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OrderDB extends Order {

    public OrderDB(int orderID, int userID, Date date, String status) {
        super(orderID, userID, date, status);
    }


    public static Collection<OrderDB> getAllOrders(){//TODO: TEST THIS METHOD
        ResultSet rs;
        ArrayList<OrderDB> orders = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * from T_Order");

            while(rs.next()){
                orders.add(new
                        OrderDB(
                        rs.getInt("orderID"),
                        rs.getInt("userID"),
                        rs.getDate("date"),
                        rs.getString("status")
                ));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    public static boolean placeOrder(){
        return OrderDB.placeOrder();
    }


}
