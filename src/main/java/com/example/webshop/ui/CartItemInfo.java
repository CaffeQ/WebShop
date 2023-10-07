package com.example.webshop.ui;

/**
 * Represents an item in the cart, along with its quantity, for the UI layer.
 */
public class CartItemInfo {

    private final ItemInfo item;
    private int quantity;

    /**
     * Constructs a new CartItemInfo object.
     *
     * @param item The item information.
     * @param quantity The quantity of the item in the cart.
     */
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
