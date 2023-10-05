package com.example.webshop.bo.handler;

import com.example.webshop.bo.Item;
import com.example.webshop.bo.Roles;
import com.example.webshop.bo.User;
import com.example.webshop.db.ItemDB;
import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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


    public static boolean adminAddItem(HttpServletRequest request){
        if(!UserHandler.isUserAdmin(request.getSession()))
            return false;
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String desc = request.getParameter("description");
        String status = request.getParameter("status");
        status = status.toUpperCase();
        ItemInfo itemInfo = new ItemInfo(name, Integer.parseInt(price),desc,Integer.parseInt(quantity) , status );
        System.out.println("Adding item: " + itemInfo.toString());
        User user = User.searchUser(userInfo.getName());
        if(!Item.isNotNULL(itemInfo))
            return false;
        if(user == null)
            return false;
        return Item.createItem(new ItemInfo(
                    itemInfo.getName(),
                    itemInfo.getPrice(),
                    itemInfo.getDescription(),
                    itemInfo.getQuantity(),
                    itemInfo.getStatus()));

    }


}
