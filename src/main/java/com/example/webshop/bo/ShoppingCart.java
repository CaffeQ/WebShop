package com.example.webshop.bo;

import com.example.webshop.bo.Interface.IShoppingCart;
import com.example.webshop.ui.ItemInfo;

import java.io.Serializable;

public class ShoppingCart implements IShoppingCart<ItemInfo, Integer>, Serializable {
    private ItemInfo itemInfo;
    private int quantity;

    public ShoppingCart(ItemInfo itemInfo, int quantity) {
        this.itemInfo = itemInfo;
        this.quantity = quantity;
    }
}
