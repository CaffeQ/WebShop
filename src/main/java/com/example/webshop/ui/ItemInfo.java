package com.example.webshop.ui;


import com.example.webshop.db.ItemDB;

import java.util.Collection;

public class ItemInfo {

    private String name;
    private String description;

    public ItemInfo(String name, String description) {
        this.name = name;
        this.description = description;
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

    public void setDesc(String desc) {this.description = desc;}

    @Override
    public String toString() {
        return "ItemInfo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}