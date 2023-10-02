package com.example.webshop.bo;

import com.example.webshop.db.ItemDB;

import java.util.ArrayList;
import java.util.Collection;

public class Item{

    //protected enum Status {IN_STOCK, OUT_OF_STOCK};

    private int id;
    private String name;
    private String description;
    private int quantity;
    private String status;



    protected Item(int id, String name, String description, int quantity, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
    }

    public static Collection searchItems(){ //TODO! <--- Add correct parameter here
        return ItemDB.searchItems();
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
}