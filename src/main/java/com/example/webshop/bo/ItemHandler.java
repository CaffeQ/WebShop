package com.example.webshop.bo;

import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ItemHandler {
    public static Collection<ItemInfo> getItems(){
        Collection<Item> c = Item.searchItems();
        ArrayList<ItemInfo> items = new ArrayList<>();
        for (Item item : c) {
            items.add(new ItemInfo(
                    item.getName(),
                    item.getPrice(),
                    item.getDesc(),
                    item.getQuantity(),
                    item.getCategory(),
                    item.getStatus()
            ));
        }
        return items;
    }

    public static ItemInfo getItemByName(String itemName){
        Item itemDB = Item.getItemIdByName(itemName);
        return new ItemInfo(itemDB.getName(), itemDB.getPrice(),itemDB.getDescription(), itemDB.getQuantity(), itemDB.getCategory(), itemDB.getStatus());
    }

    protected static ItemInfo itemToItemInfo(Item item){
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
        int itemID = Item.getItemIdByName(previousName).getId();
        Item.editItem(new Item(itemID,name,price,desc, quantity,category,status));
        return true;
    }
    public static boolean addItem(HttpServletRequest request) throws SQLException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        User user = User.searchUser(userInfo.getName());
        if(user == null)
            return false;

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String desc = request.getParameter("description");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        status = status.toUpperCase();

        Item item = new Item(0, name, Integer.parseInt(price), desc, Integer.parseInt(quantity), category, status);
        if(!Item.isNotNULL(item))
            return false;

        return Item.createItem(item);
    }

}
