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

    public boolean add(String itemName, String quantity) {

        for(int i=0;i<cartList.size();i++){
            if(cartList.get(i).getItem().getName().compareTo(itemName)==0){
                int nrOfItemInStock = cartList.get(i).getItem().getQuantity();
                int nrOfItemInCart = Integer.parseInt(cartList.get(i).getQuantity());
                int nrOfItemRequested = Integer.parseInt(quantity);

                if(nrOfItemInStock < nrOfItemInCart+nrOfItemRequested){
                    return false;
                }
                cartList.get(i).setQuantity(String.valueOf(nrOfItemInCart+nrOfItemRequested));
                return true;
            }
        }

        Item item = Item.getItemIdByName(itemName);
        if(item.getQuantity()<=0) return false;

        cartList.add(new CartItem<Item>(item,quantity));
        CartItem<Item> cartItem = new CartItem<>(item,quantity);
        return true;
    }

}
