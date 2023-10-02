package com.example.webshop.bo;

import com.example.webshop.db.ItemDB;

import java.util.ArrayList;
import java.util.Collection;

public class Item{

    private int id;
    private String name;
    private String description;

    protected Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    static public Collection searchItems(){ //TODO! <--- Add correct parameter here
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
}