package com.example.webshop.bo;

import com.example.webshop.db.ItemDB;

import java.util.ArrayList;
import java.util.Collection;

public class Item{

    private int id;
    private String name;
    private int price;
    private String description;
    private int quantity;
    private String status;

    protected Item(int id, String name, int price, String description, int quantity, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
    }

    public static Collection<ItemDB> searchItems(){ //TODO! <--- Add correct parameter here
        return ItemDB.searchItems();
    }
    public static ItemDB getItemIdByName(String name){return ItemDB.getItemByName(name);}
    public static ArrayList<ItemDB> getItemsIdByOrderID(int orderID){return ItemDB.getItemsIdByOrderID(orderID);}


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
                ", status='" + status + '\'' +
                '}';
    }
}