package com.example.webshop.bo;

import com.example.webshop.db.OrderDB;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class Order {

   private final int orderID;
   private final int userID;
   private final Date date;
   private String status;

   private final ArrayList<CartItem> items;

    protected Order(int orderID, int userID, Date date, String status, ArrayList<CartItem> items) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.status = status;
        this.items = items;
    }

    protected static Collection<Order> getAllOrders(){
        return OrderDB.getAllOrders();
    }

    protected static boolean placeOrder(Cart cart, User user) throws SQLException {
        Order order = new Order(0,user.getId(),Date.valueOf(LocalDateTime.now().toLocalDate()),"active",cart.getCart());
        return OrderDB.placeOrder(order, user);
    }

    public ArrayList<CartItem> getItems() {
        ArrayList<CartItem> deepCopy = new ArrayList<>();
        for(CartItem cartItem : items){
            deepCopy.add(new CartItem(
                    new Item(
                            cartItem.getItem().getId(),
                            cartItem.getItem().getName(),
                            cartItem.getItem().getPrice(),
                            cartItem.getItem().getDescription(),
                            cartItem.getItem().getQuantity(),
                            cartItem.getItem().getCategory(),
                            cartItem.getItem().getStatus(),
                            cartItem.getItem().isActive()
                    ),
                    cartItem.getQuantity()));
        }
        return deepCopy;
    }

    protected static boolean sendOrder(int orderID){
        return OrderDB.sendOrder(orderID);
    }

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