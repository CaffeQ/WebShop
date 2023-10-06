package com.example.webshop.bo;

import com.example.webshop.ui.OrderInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class OrderHandler {

    public static ArrayList<OrderInfo> getAll(){
        Collection<Order> orders =  Order.getAllOrders();
        ArrayList<OrderInfo> copy = new ArrayList<>();

        for(Order o : orders){
            ArrayList<CartItem> itemInfos = new ArrayList<>();

            for(CartItem cartItem : o.getItems()){
                itemInfos.add(new CartItem(cartItem.getItem(), cartItem.getQuantity()));
            }
            copy.add(new OrderInfo(o.getOrderID(), o.getUserID(), o.getDate(), o.getStatus(),itemInfos));
        }
        return copy;
    }

    public static boolean placeOrder(HttpSession session) throws SQLException {
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart.getCart().isEmpty()) return false;
        session.removeAttribute("cartList");
        session.removeAttribute("cart");
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        return Order.placeOrder(cart,User.searchUser(userInfo.getName()));
    }

    public static boolean sendOrder(HttpServletRequest request) throws SQLException {
        int orderID = Integer.parseInt(request.getParameter("sendOrderID"));
        Order order = Order.getOrderByID(orderID);
        if(order.getStatus().equals("active")) return Order.sendOrder(orderID);
        return false;
    }


}
