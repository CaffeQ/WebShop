package com.example.webshop.bo;

public class  CartItem<T>{
    private final T item;
    private int quantity;

    public CartItem(T item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public T getItem() {
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
        return "CartItem{" +
                "item=" + item.toString() +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}