package com.example.webshop.bo;

import com.example.webshop.ui.ItemInfo;

import java.util.ArrayList;

public class Cart{

    private static ArrayList<CartItem<Item>> cart;


    public static void init(){
        cart = new ArrayList<CartItem<Item>>();
    }

    public static ArrayList<CartItem<ItemInfo>> getCart() {
        ArrayList<CartItem<ItemInfo>> copy = new ArrayList<>();
        for (CartItem<Item> cartItem : cart) {
            copy.add(new CartItem<ItemInfo>(ItemHandler.itemToItemInfo(cartItem.getItem()), cartItem.getQuantity()));
        }
        return copy;
    }

    public static CartItem<ItemInfo> add(String itemName, String quantity) {
        Item item = Item.getItemIdByName(itemName);
        CartItem<Item> cartItem = new CartItem<>(item,quantity);
        return new CartItem<ItemInfo>(ItemHandler.itemToItemInfo(cartItem.getItem()), cartItem.getQuantity());
    }


}
