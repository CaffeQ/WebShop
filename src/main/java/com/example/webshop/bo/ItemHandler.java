package com.example.webshop.bo;

import com.example.webshop.db.ItemDB;
import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
                    itemDB.getCategory(),
                    itemDB.getStatus()
            ));
        }
        return items;
    }

    public static ItemInfo getItemByName(String itemName){
        ItemDB itemDB = Item.getItemIdByName(itemName);
        return new ItemInfo(itemDB.getName(), itemDB.getPrice(),itemDB.getDescription(), itemDB.getQuantity(), itemDB.getCategory(), itemDB.getStatus());
    }

    public static ItemInfo itemToItemInfo(Item item){
        return new ItemInfo(item.getName(), item.getPrice(), item.getDescription(), item.getQuantity(), item.getCategory(), item.getStatus());
    }

    public static boolean editItem(HttpServletRequest request){
        String previousName = request.getParameter("previousName");
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String desc = request.getParameter("description");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        Item.editItem(new ItemInfo(name,price,desc, quantity,category,status), previousName);



        return true;
    }
    public static boolean addItem(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String desc = request.getParameter("description");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        status = status.toUpperCase();
        ItemInfo itemInfo = new ItemInfo(name, Integer.parseInt(price),desc,Integer.parseInt(quantity),category, status );
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
                    itemInfo.getCategory(),
                    itemInfo.getStatus()));
    }

}
