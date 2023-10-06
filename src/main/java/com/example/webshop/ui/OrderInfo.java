package com.example.webshop.ui;

import com.example.webshop.bo.CartItem;

import java.sql.Date;
import java.util.ArrayList;

public class OrderInfo {

    private final int orderID;
    private final int userID;
    private final Date date;
    private String status;
    private final ArrayList<CartItem> items;

    public OrderInfo(int orderID, int userID, Date date, String status, ArrayList<CartItem> items) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.status = status;
        this.items = items;
    }


    public int getOrderID() {return orderID;}

    public int getUserID() {
        return userID;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {return status;}

    public ArrayList<CartItem> getItems() {return items;}
}
