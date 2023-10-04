package com.example.webshop.bo.handler;

import com.example.webshop.bo.Item;
import com.example.webshop.db.ItemDB;
import com.example.webshop.ui.ItemInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemHandler {
    public static Collection<ItemInfo> getItems(){
        Collection<ItemDB> c = Item.searchItems();
        ArrayList<ItemInfo> items = new ArrayList<>();
        for (ItemDB itemDB : c) {
            items.add(new ItemInfo(
                    itemDB.getName(),
                    itemDB.getPrice(),
                    itemDB.getDesc(),
                    itemDB.getQuantity(),
                    itemDB.getStatus()
            ));
        }
        return items;
    }

    public static ItemInfo getItemByName(String itemName){
        ItemDB itemDB = Item.getItemIdByName(itemName);
        return new ItemInfo(itemDB.getName(), itemDB.getPrice(),itemDB.getDescription(), itemDB.getQuantity(), itemDB.getStatus());
    }

    public static ItemInfo itemToItemInfo(Item item){
        return new ItemInfo(item.getName(), item.getPrice(), item.getDescription(), item.getQuantity(), item.getStatus());
    }


}
