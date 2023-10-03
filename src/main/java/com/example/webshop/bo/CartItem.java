package com.example.webshop.bo;

import com.example.webshop.bo.Interface.ICartItem;
import com.example.webshop.ui.ItemInfo;

import java.io.Serializable;

public class CartItem implements ICartItem<Item, Integer> {
    private Item itemInfo;
    private int quantity;

    public CartItem(Item itemInfo, int quantity) {
        this.itemInfo = itemInfo;
        this.quantity = quantity;
    }
}
