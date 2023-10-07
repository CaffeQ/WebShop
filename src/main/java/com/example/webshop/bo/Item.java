package com.example.webshop.bo;

import com.example.webshop.db.ItemDB;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Represents an individual item available in the webshop.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class Item{

    private int id;
    private String name;
    private int price;
    private String description;
    private int quantity;
    private String category;
    private String status;
    private boolean active;

    /**
     * Initializes a new Item with all the given attributes.
     *
     * @param id Unique identifier.
     * @param name Name of the item.
     * @param price Price of the item.
     * @param description Description of the item.
     * @param quantity Quantity available.
     * @param category Category of the item.
     * @param status Status of the item.
     * @param active Indicates if the item is active or not.
     */
    protected Item(int id, String name, int price, String description, int quantity, String category, String status, boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.status = status;
        this.active = active;
    }

    /**
     * Searches for items in the database.
     *
     * @return Collection<Item> Collection of items found.
     */
    protected static Collection<Item> searchItems(){
        return ItemDB.searchItems();
    }

    /**
     * Retrieves an item by its name from the database.
     *
     * @param name Name of the item.
     * @return Item object with the given name.
     */
    protected static Item getItemIdByName(String name){return ItemDB.getItemByName(name);}

    /**
     * Creates a new item in the database.
     *
     * @param item Item object to be created.
     * @return boolean True if the creation was successful, otherwise false.
     * @throws SQLException In case of SQL errors.
     */
    protected static boolean createItem(Item item) throws SQLException {
        return ItemDB.createItem( item );
    }

    /**
     * Removes an item from the database.
     *
     * @param item Item object to be removed.
     * @return boolean True if the removal was successful, otherwise false.
     */
    protected static boolean removeItem(Item item) {
        return ItemDB.removeItem(item);
    }

    /**
     * Edits an existing item in the database.
     *
     * @param item Item object with updated attributes.
     * @return boolean True if the edit was successful, otherwise false.
     */
    protected static boolean editItem(Item item){
        return ItemDB.editItem( item );
    }

    /**
     * Checks if an item object is not null and has essential attributes.
     *
     * @param item Item object to be checked.
     * @return boolean True if the item is not null and has essential attributes, otherwise false.
     */
    protected static boolean isNotNULL(Item item){
        if(item == null)
            return false;
        return item.getName() != null || item.getName().isEmpty() ||
                item.getDescription() != null ||
                item.getStatus() != null;

    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {return price;}

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}