package com.example.webshop.bo;

import com.example.webshop.ui.ItemInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemHandler {
    public static Collection<ItemInfo> getItems(){
        Collection c = Item.searchItems();
        ArrayList<ItemInfo> items = new ArrayList<>();
        for(Iterator it = c.iterator(); it.hasNext();){
            Item item = (Item) it.next();
            items.add(new ItemInfo(
                    item.getName(),
                    item.getPrice(),
                    item.getDesc(),
                    item.getQuantity(),
                    item.getStatus()
            ));
        }
        return items;
    }
}
