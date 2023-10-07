package com.example.webshop.bo;

import com.example.webshop.db.OrderDB;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents an order placed by a user in the webshop.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class Order {

   private final int orderID;
   private final int userID;
   private final Date date;
   private String status;

   private final ArrayList<CartItem> items;

    /**
     * Initializes a new Order with all the given attributes.
     *
     * @param orderID Unique identifier for the order.
     * @param userID Identifier for the user who placed the order.
     * @param date Date the order was placed.
     * @param status Status of the order.
     * @param items List of CartItem objects in the order.
     */
    protected Order(int orderID, int userID, Date date, String status, ArrayList<CartItem> items) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.status = status;
        this.items = items;
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return Collection<Order> Collection of all orders.
     */
    protected static Collection<Order> getAllOrders(){
        return OrderDB.getAllOrders();
    }

    /**
     * Retrieves orders by their status from the database.
     *
     * @param status Status of the orders to fetch.
     * @return Collection<Order> Collection of orders with the given status.
     */
    protected static Collection<Order> getOrderByStatus(String status){
        return OrderDB.getOrderByStatus(status);
    }

    /**
     * Places a new order in the database.
     *
     * @param cart Cart object containing items to order.
     * @param user User object who is placing the order.
     * @return boolean True if the order was successfully placed, otherwise false.
     * @throws SQLException In case of SQL errors.
     */
    protected static boolean placeOrder(Cart cart, User user) throws SQLException {
        Order order = new Order(0,user.getId(),Date.valueOf(LocalDateTime.now().toLocalDate()),"active",cart.getCart());
        return OrderDB.placeOrder(order, user);
    }


    /**
     * Retrieves the list of CartItem objects associated with this order.
     *
     * @return ArrayList<CartItem> The original list of CartItems in the order.
     */
    public ArrayList<CartItem> getItems() {
        return this.items;
    }

    /**
     * Sends an order based on its ID.
     *
     * @param orderID Unique identifier for the order to send.
     * @return boolean True if the order was successfully sent, otherwise false.
     */
    protected static boolean sendOrder(int orderID){
        return OrderDB.sendOrder(orderID);
    }

    /**
     * Retrieves an order by its ID from the database.
     *
     * @param orderID Unique identifier for the order to fetch.
     * @return Order Order object with the given ID.
     */
    protected static Order getOrderByID(int orderID){
        return OrderDB.getOrderByID(orderID);
    }

    public int getOrderID() {
        return orderID;
    }

    public int getUserID() {
        return userID;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {this.status = status;}

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}