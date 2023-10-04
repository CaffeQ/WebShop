package com.example.webshop.bo;

import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.UserInfo;

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

    public static boolean adminAddItem(UserInfo userInfo, ItemInfo itemInfo){
        User user = User.searchUser(userInfo.getName());
        if(!Item.isNotNULL(itemInfo))
            return false;
        if(user == null)
            return false;
        if(user.getRole().equals( Roles.ADMIN ) && user.getToken().equals(userInfo.getToken())) {
            return Item.createItem(new Item(
                    0,
                    itemInfo.getName(),
                    itemInfo.getPrice(),
                    itemInfo.getDescription(),
                    itemInfo.getQuantity(),
                    itemInfo.getStatus()));
        }else
            return false;
    }
}
