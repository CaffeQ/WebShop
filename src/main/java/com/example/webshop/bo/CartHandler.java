package com.example.webshop.bo;

import com.example.webshop.ui.CartItemInfo;
import com.example.webshop.ui.ItemInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

public class CartHandler {

    public static Cart createNewCart() {
        return new Cart();
    }
    public static ArrayList<CartItemInfo> createNewCartList() {
        return new ArrayList<>();
    }


    public static ArrayList<CartItemInfo> getCartList(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        ArrayList<CartItemInfo> copy = new ArrayList<>();
        for (CartItem cartItem : cart.getCart()) {
            copy.add(new CartItemInfo(new ItemInfo(cartItem.getItem().getName(),cartItem.getItem().getPrice(), cartItem.getItem().getDescription(),cartItem.getItem().getQuantity(), cartItem.getItem().getCategory(), cartItem.getItem().getStatus()), cartItem.getQuantity()));
        }
        request.getSession().setAttribute("cartList", copy);
        return copy;
    }

    public static void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkCartEmpty(session);
        Cart cart = (Cart) session.getAttribute("cart");
        String itemName = request.getParameter("cartItemName");
        String itemQuantity = request.getParameter("cartItemQuantity");
        cart.add(itemName, itemQuantity);
        getCartList(request);
    }

    public static void checkCartEmpty(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        ArrayList<CartItemInfo> cartList = (ArrayList<CartItemInfo>) session.getAttribute("cartList");
        if(cart != null && cartList != null){
            return;
        }
        session.setAttribute("cart",CartHandler.createNewCart());
        session.setAttribute("cartList",CartHandler.createNewCartList());
    }
}
