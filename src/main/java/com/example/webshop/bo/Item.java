package com.example.webshop.bo;

import com.example.webshop.db.ItemDB;

import java.sql.SQLException;
import java.util.Collection;

public class Item{

    private int id;
    private String name;
    private int price;
    private String description;
    private int quantity;
    private String category;
    private String status;
    private boolean active;


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

    protected static Collection<Item> searchItems(){
        return ItemDB.searchItems();
    }
    protected static Item getItemIdByName(String name){return ItemDB.getItemByName(name);}

    protected static boolean createItem(Item item) throws SQLException {
        return ItemDB.createItem( item );
    }
    protected static boolean removeItem(Item item) {
        return ItemDB.removeItem(item);
    }
    protected static boolean editItem(Item item){
        return ItemDB.editItem( item );
    }

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