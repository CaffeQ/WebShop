package com.example.webshop.bo;

import com.example.webshop.ui.ItemInfo;

import java.util.ArrayList;

public class Cart{

    private final ArrayList<CartItem<Item>> cartList;


    protected Cart() {
        cartList = new ArrayList<>();
    }

    public ArrayList<CartItem<ItemInfo>> getCartPresentation() {
        ArrayList<CartItem<ItemInfo>> copy = new ArrayList<>();
        for (CartItem<Item> cartItem : cartList) {
            copy.add(new CartItem<>(ItemHandler.itemToItemInfo(cartItem.getItem()), cartItem.getQuantity()));
        }
        return copy;
    }

    public boolean add(String itemName, String quantity) {//TODO: Adding multiple of same item is strange
        Item item = Item.getItemIdByName(itemName);
        if(item == null) return false;
        if(item.getQuantity()<=0 || item.getQuantity() < Integer.parseInt(quantity)) return false;

        for (CartItem<Item> itemCartItem : cartList) {

            if (itemCartItem.getItem().getName().compareTo(itemName) == 0) {
                int nrOfItemInStock = item.getQuantity();
                int nrOfItemInCart = itemCartItem.getQuantity();
                int nrOfItemRequested = Integer.parseInt(quantity);

                if (nrOfItemInStock < nrOfItemInCart + nrOfItemRequested) {
                    return false;
                }
                itemCartItem.setQuantity(nrOfItemInCart + nrOfItemRequested);
                break;
            }
        }
        cartList.add(new CartItem<>(item,Integer.parseInt(quantity)));
        return true;
    }

    public ArrayList<CartItem<Item>> getCartApplication(){
        ArrayList<CartItem<Item>> deepCopy = new ArrayList<>();
        for(CartItem<Item> cartItem : cartList){
            deepCopy.add(
                    new CartItem<>(
                            new Item(
                                    cartItem.getItem().getId(),
                                    cartItem.getItem().getName(),
                                    cartItem.getItem().getPrice(),
                                    cartItem.getItem().getDescription(),
                                    cartItem.getItem().getQuantity(),
                                    cartItem.getItem().getCategory(),
                                    cartItem.getItem().getStatus()
                                    ),
                            cartItem.getQuantity()
                            ));
        }
        return deepCopy;
    }

    }
