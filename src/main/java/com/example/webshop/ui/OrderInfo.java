package com.example.webshop.ui;

import com.example.webshop.bo.CartItem;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Represents detailed information about an order for the UI layer.
 * Holds information such as order ID, user ID, order date, status, and list of items.
 */
public class OrderInfo {

    private final int orderID;
    private final int userID;
    private final Date date;
    private String status;
    private final ArrayList<CartItemInfo> items;

    /**
     * Constructs a new OrderInfo object to encapsulate detailed order information.
     *
     * @param orderID The unique identifier for the order.
     * @param userID The unique identifier for the user who made the order.
     * @param date The date the order was made.
     * @param status The status of the order.
     * @param items The list of items in the order.
     */
    public OrderInfo(int orderID, int userID, Date date, String status, ArrayList<CartItemInfo> items) {
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

    public ArrayList<CartItemInfo> getItems() {return items;}
}
