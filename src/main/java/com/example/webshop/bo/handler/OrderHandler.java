package com.example.webshop.bo.handler;

import com.example.webshop.bo.CartItem;
import com.example.webshop.bo.Item;
import com.example.webshop.bo.Order;
import com.example.webshop.db.ItemDB;
import com.example.webshop.db.OrderDB;
import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.OrderInfo;

import java.util.ArrayList;
import java.util.Collection;

public class OrderHandler {

    public static ArrayList<OrderInfo> getAll(){
        Collection<OrderDB> orders =  Order.getAllOrders();
        ArrayList<OrderInfo> copy = new ArrayList<>();

        for(OrderDB o : orders){
            ArrayList<CartItem<ItemInfo>> itemInfos = new ArrayList<>();

            for(CartItem<ItemDB> cartItem : o.getPurchaseItems()){
                itemInfos.add(new CartItem<>(ItemHandler.itemToItemInfo(cartItem.getItem()), String.valueOf(cartItem.getQuantity())));
            }
            copy.add(new OrderInfo(o.getOrderID(), o.getUserID(), o.getDate(), o.getStatus(),itemInfos));
        }
        return copy;
    }

    public static ArrayList<OrderInfo> getAllActive(){//TODO: Implement method
        return new ArrayList<>();
    }

}
