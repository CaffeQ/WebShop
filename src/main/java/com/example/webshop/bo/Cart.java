package com.example.webshop.bo;

import java.util.ArrayList;

public class Cart{

    private final ArrayList<CartItem> cartList;

    protected Cart() {
        cartList = new ArrayList<>();
    }

    protected ArrayList<CartItem> getCart() {
        return cartList;
    }

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
