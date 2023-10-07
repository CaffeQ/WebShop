package com.example.webshop.bo;

import com.example.webshop.ui.CartItemInfo;
import com.example.webshop.ui.ItemInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

/**
 * Manages operations related to the Cart such as adding, removing items, and initializing cart.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class CartHandler {

    /**
     * Creates and returns a new empty Cart object.
     *
     * @return Cart An empty Cart object.
     */
    public static Cart createNewCart() {
        return new Cart();
    }

    /**
     * Creates and returns a new empty ArrayList of CartItemInfo.
     *
     * @return ArrayList<CartItemInfo> An empty list of CartItemInfo objects.
     */
    public static ArrayList<CartItemInfo> createNewCartList() {
        return new ArrayList<>();
    }

    /**
     * Removes an item from the cart based on the item name received from the HttpServletRequest.
     *
     * @param request HttpServletRequest containing the parameter "removeItemName".
     * @return boolean True if item removed successfully, otherwise false.
     */
    public static boolean removeItem(HttpServletRequest request){
        String itemName = request.getParameter("removeItemName");
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        for(int i=0;i<cart.getCart().size();i++){
            if(cart.getCart().get(i).getItem().getName().equals(itemName)){
                cart.getCart().remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the list of cart items and returns it as a list of CartItemInfo objects.
     *
     * @param request HttpServletRequest used to fetch the Cart object.
     * @return ArrayList<CartItemInfo> List of CartItemInfo objects.
     */
    public static ArrayList<CartItemInfo> getCartList(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        ArrayList<CartItemInfo> copy = new ArrayList<>();
        for (CartItem cartItem : cart.getCart()) {
            copy.add(new CartItemInfo(new ItemInfo(cartItem.getItem().getName(),cartItem.getItem().getPrice(), cartItem.getItem().getDescription(),cartItem.getItem().getQuantity(), cartItem.getItem().getCategory(), cartItem.getItem().getStatus()), cartItem.getQuantity()));
        }
        request.getSession().setAttribute("cartList", copy);
        return copy;
    }

    /**
     * Adds an item to the cart based on the parameters received from the HttpServletRequest.
     *
     * @param request HttpServletRequest containing the parameters "cartItemName" and "cartItemQuantity".
     */
    public static void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkCartEmpty(session);
        Cart cart = (Cart) session.getAttribute("cart");
        String itemName = request.getParameter("cartItemName");
        String itemQuantity = request.getParameter("cartItemQuantity");
        cart.add(itemName, itemQuantity);
        getCartList(request);
    }

    /**
     * Checks if the Cart and cartList in the session are null. Initializes them if they are null.
     *
     * @param session HttpSession object used to check and set attributes.
     */
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
