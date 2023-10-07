package com.example.webshop.bo;

import java.util.ArrayList;

/**
 * Represents a shopping cart in a webshop. The cart contains a list of CartItem objects.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class Cart{

    /**
     * Holds the list of cart items.
     */
    private final ArrayList<CartItem> cartList;

    /**
     * Initializes an empty cart.
     */
    protected Cart() {
        cartList = new ArrayList<>();
    }

    /**
     * Returns the list of cart items.
     *
     * @return ArrayList<CartItem> the list of cart items.
     */
    protected ArrayList<CartItem> getCart() {
        return cartList;
    }

    /**
     * Adds an item to the cart based on the item name and quantity.
     *
     * @param itemName Name of the item to be added.
     * @param quantity Quantity of the item to be added.
     * @return boolean True if the item was successfully added, otherwise false.
     */
    protected boolean add(String itemName, String quantity) {
        Item item = Item.getItemIdByName(itemName);
        if(item == null) return false;
        if(item.getQuantity()<=0 || item.getQuantity() < Integer.parseInt(quantity)) return false;

        boolean inCart = false;
        for (CartItem itemCartItem : cartList) {

            if (itemCartItem.getItem().getName().compareTo(itemName) == 0) {
                int nrOfItemInStock = item.getQuantity();
                int nrOfItemInCart = itemCartItem.getQuantity();
                int nrOfItemRequested = Integer.parseInt(quantity);
                inCart = true;

                if (nrOfItemInStock < nrOfItemInCart + nrOfItemRequested) {
                    return false;
                }
                itemCartItem.setQuantity(nrOfItemInCart + nrOfItemRequested);
                break;
            }
        }

        if(!inCart){
            cartList.add(new CartItem(item,Integer.parseInt(quantity)));
        }

        return true;
    }

}
