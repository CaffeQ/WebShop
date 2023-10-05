package com.example.webshop.bo.handler;

import com.example.webshop.bo.Cart;
import com.example.webshop.bo.CartItem;
import com.example.webshop.bo.Order;
import com.example.webshop.bo.User;
import com.example.webshop.db.ItemDB;
import com.example.webshop.db.OrderDB;
import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.OrderInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class OrderHandler {

    public static ArrayList<OrderInfo> getAll(){
        Collection<OrderDB> orders =  Order.getAllOrders();
        ArrayList<OrderInfo> copy = new ArrayList<>();

        for(OrderDB o : orders){
            ArrayList<CartItem<ItemInfo>> itemInfos = new ArrayList<>();

            for(CartItem<ItemDB> cartItem : o.getPurchaseItems()){
                itemInfos.add(new CartItem<>(ItemHandler.itemToItemInfo(cartItem.getItem()), cartItem.getQuantity()));
            }
            copy.add(new OrderInfo(o.getOrderID(), o.getUserID(), o.getDate(), o.getStatus(),itemInfos));
        }
        return copy;
    }

    public static boolean placeOrder(HttpSession session) throws SQLException {
        Cart cart = (Cart) session.getAttribute("cart");
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        return Order.placeOrder(cart.getCartApplication(),User.searchUser(userInfo.getName()));
    }

    public static boolean sendOrder(HttpServletRequest request) throws SQLException {
        int orderID = Integer.parseInt(request.getParameter("sendOrderID"));
        Order order = Order.getOrderByID(orderID);
        System.out.println("order.status: " + order.getStatus());
        if(order.getStatus().equals("active")) return Order.sendOrder(orderID);
        return false;
    }

    public static ArrayList<OrderInfo> getAllActive(){//TODO: Implement method
        return new ArrayList<>();
    }

    public static Cart createNewCart() {
        return new Cart();
    }

    public static boolean addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkCartEmpty(session);
        Cart cart = (Cart) session.getAttribute("cart");
        String itemName = request.getParameter("cartItemName");
        String itemQuantity = request.getParameter("cartItemQuantity");
        return cart.add(itemName,itemQuantity);
    }

    public static void checkCartEmpty(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null){
            return;
        }
        session.setAttribute("cart",OrderHandler.createNewCart());
    }

}
