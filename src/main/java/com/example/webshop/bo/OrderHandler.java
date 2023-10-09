package com.example.webshop.bo;

import com.example.webshop.ui.CartItemInfo;
import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.OrderInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Handles operations related to Order objects such as fetching, placing, and sending orders.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class OrderHandler {

    /**
     * Retrieves all orders and converts them to OrderInfo objects.
     *
     * @return ArrayList<OrderInfo> List of OrderInfo objects representing all orders.
     */
    public static ArrayList<OrderInfo> getAll(){
        Collection<Order> orders =  Order.getAllOrders();
        ArrayList<OrderInfo> copy = new ArrayList<>();

        for(Order o : orders){
            ArrayList<CartItemInfo> itemInfos = new ArrayList<>();

            for(CartItem cartItem : o.getItems()){
                itemInfos.add(new CartItemInfo(new ItemInfo(cartItem.getItem().getName(),cartItem.getItem().getPrice(),cartItem.getItem().getDescription(),cartItem.getItem().getQuantity(),cartItem.getItem().getCategory(),cartItem.getItem().getStatus()), cartItem.getQuantity()));
            }
            copy.add(new OrderInfo(o.getOrderID(), o.getUserID(), o.getDate(), o.getStatus(),itemInfos));
        }
        return copy;
    }

    /**
     * Retrieves orders based on their status and converts them to OrderInfo objects.
     *
     * @param status The status of the orders to fetch.
     * @return ArrayList<OrderInfo> List of OrderInfo objects representing orders with the given status.
     */
    public static ArrayList<OrderInfo> getOrderByStatus(String status){
        Collection<Order> orders =  Order.getOrderByStatus(status);
        ArrayList<OrderInfo> copy = new ArrayList<>();

        for(Order o : orders){
            ArrayList<CartItemInfo> itemInfos = new ArrayList<>();

            for(CartItem cartItem : o.getItems()){
                itemInfos.add(new CartItemInfo(new ItemInfo(cartItem.getItem().getName(),cartItem.getItem().getPrice(),cartItem.getItem().getDescription(),cartItem.getItem().getQuantity(),cartItem.getItem().getCategory(),cartItem.getItem().getStatus()), cartItem.getQuantity()));
            }
            copy.add(new OrderInfo(o.getOrderID(), o.getUserID(), o.getDate(), o.getStatus(),itemInfos));
        }
        return copy;
    }

    /**
     * Places a new order based on the current session.
     *
     * @param session HttpSession object containing cart and user information.
     * @return boolean True if the order was successfully placed, otherwise false.
     * @throws SQLException In case of SQL errors.
     */
    public static boolean placeOrder(HttpSession session) throws SQLException {
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart.getCart().isEmpty()) return false;
        session.removeAttribute("cartList");
        session.removeAttribute("cart");
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        return Order.placeOrder(cart,User.searchUser(userInfo.getEmail()));
    }

    /**
     * Sends an order based on the parameter received from HttpServletRequest.
     *
     * @param request HttpServletRequest containing the parameter "sendOrderID".
     * @return boolean True if the order was successfully sent, otherwise false.
     * @throws SQLException In case of SQL errors.
     */
    public static boolean sendOrder(HttpServletRequest request) throws SQLException {
        int orderID = Integer.parseInt(request.getParameter("sendOrderID"));
        Order order = Order.getOrderByID(orderID);
        if(order.getStatus().equals("active")) return Order.sendOrder(orderID);
        return false;
    }


}
