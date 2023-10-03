package com.example.webshop.bo;

import com.example.webshop.ui.ItemInfo;

import java.util.ArrayList;

public class Cart{

    private ArrayList<CartItem<Item>> cartList;


    public Cart() {
        cartList = new ArrayList<CartItem<Item>>();;
    }

    public ArrayList<CartItem<ItemInfo>> getCart() {
        ArrayList<CartItem<ItemInfo>> copy = new ArrayList<>();
        for (CartItem<Item> cartItem : cartList) {
            copy.add(new CartItem<ItemInfo>(ItemHandler.itemToItemInfo(cartItem.getItem()), cartItem.getQuantity()));
        }
        return copy;
    }

    public CartItem<ItemInfo> add(String itemName, String quantity) {
        Item item = Item.getItemIdByName(itemName);
        cartList.add(new CartItem<Item>(item,quantity));
        CartItem<Item> cartItem = new CartItem<>(item,quantity);
        return new CartItem<ItemInfo>(ItemHandler.itemToItemInfo(cartItem.getItem()), cartItem.getQuantity());
    }

}
