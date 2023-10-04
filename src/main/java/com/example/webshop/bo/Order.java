package com.example.webshop.bo;

import com.example.webshop.db.ItemDB;
import com.example.webshop.db.OrderDB;
import com.example.webshop.db.PurchaseItemDB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class Order {

   private final int orderID;
   private final int userID;
   private final Date date;
   private String status;

   private final ArrayList<CartItem<ItemDB>> items;

    protected Order(int orderID, int userID, Date date, String status) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.status = status;
        this.items = PurchaseItemDB.getCartItemByOrderID(orderID);
    }


    public static Collection<OrderDB> getAllOrders(){
        return OrderDB.getAllOrders();
    }

    public static boolean placeOrder(ArrayList<CartItem<Item>> cartList) throws SQLException {
        return OrderDB.placeOrder(cartList);
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

    public void setStatus(String status) {
        this.status = status; // 'pack', 'sent', 'cancelled'
    }

    public ArrayList<CartItem<ItemDB>> getPurchaseItems() {
        return items;
    }
}