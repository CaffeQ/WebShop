package com.example.webshop.bo;

import com.example.webshop.bo.handler.ItemHandler;
import com.example.webshop.ui.ItemInfo;

import java.sql.SQLException;
import java.util.ArrayList;

public class Cart{

    private final ArrayList<CartItem<Item>> cartList;


    public Cart() {
        cartList = new ArrayList<>();
    }

    public ArrayList<CartItem<ItemInfo>> getCart() {
        ArrayList<CartItem<ItemInfo>> copy = new ArrayList<>();
        for (CartItem<Item> cartItem : cartList) {
            copy.add(new CartItem<>(ItemHandler.itemToItemInfo(cartItem.getItem()), cartItem.getQuantity()));
        }
        return copy;
    }

    public boolean add(String itemName, String quantity) {

        for (CartItem<Item> itemCartItem : cartList) {
            if (itemCartItem.getItem().getName().compareTo(itemName) == 0) {
                int nrOfItemInStock = itemCartItem.getItem().getQuantity();
                int nrOfItemInCart = itemCartItem.getQuantity();
                int nrOfItemRequested = Integer.parseInt(quantity);

                if (nrOfItemInStock < nrOfItemInCart + nrOfItemRequested) {
                    return false;
                }
                itemCartItem.setQuantity(nrOfItemInCart + nrOfItemRequested);
                return true;
            }
        }

        Item item = Item.getItemIdByName(itemName);
        if(item.getQuantity()<=0) return false;

        cartList.add(new CartItem<>(item,Integer.parseInt(quantity)));
        return true;
    }

    public boolean placeOrder() throws SQLException {
        if(cartList == null) return false;
        if(cartList.size()==0) return false;
        return Order.placeOrder(cartList);
    }


    }
