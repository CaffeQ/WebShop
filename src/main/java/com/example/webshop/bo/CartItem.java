package com.example.webshop.bo;

public class  CartItem<T>{
    private T item;
    private String quantity;

    protected CartItem(T item, String quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public T getItem() {
        return item;
    }

    public String getQuantity() {
        return quantity;
    }
}