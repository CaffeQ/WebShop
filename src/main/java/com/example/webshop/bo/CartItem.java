package com.example.webshop.bo;

/**
 * Represents an item in the shopping cart, including the Item object and its quantity.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class  CartItem{

    private final Item item;

    private int quantity;

    /**
     * Initializes a new CartItem with the given Item object and quantity.
     *
     * @param item The Item object representing the item.
     * @param quantity The quantity of the item.
     */
    protected CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Returns the Item object representing the item in the cart.
     *
     * @return Item The Item object.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the quantity of the item in the cart.
     *
     * @return int The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item in the cart.
     *
     * @param quantity The new quantity for the item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}