package com.example.webshop.ui;

import com.example.webshop.bo.Item;

public class CartItemInfo {

    private final ItemInfo item;
    private int quantity;

    public CartItemInfo(ItemInfo item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ItemInfo getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemInfo{" +
                "item=" + item.toString() +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
