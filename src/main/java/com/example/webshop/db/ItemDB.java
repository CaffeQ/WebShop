package com.example.webshop.db;

import com.example.webshop.bo.Item;

import java.util.ArrayList;
import java.util.Collection;

public class ItemDB extends Item {

    public Collection getLocalItems(){
        ArrayList<ItemDB> items = new ArrayList<>();

        items.add(new ItemDB(1,"Mug","Blue coffe mug"));
        items.add(new ItemDB(2,"Cup","Red cup"));

        return items;
    }
    protected ItemDB(int id, String name, String desc) {
        super(id, name, desc);
    }
}