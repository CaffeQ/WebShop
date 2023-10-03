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

    public void setQuantity(String quantity) {
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