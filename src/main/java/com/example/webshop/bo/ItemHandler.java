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
        if(!checkItemParameters(name,price,quantity,desc,category,status))
            return false;
        status = status.toUpperCase();

        Item item = new Item(0, name, Integer.parseInt(price), desc, Integer.parseInt(quantity), category, status);

        return Item.createItem(item);
    }
    private static boolean checkItemParameters(String name, String price, String quantity, String desc, String category, String status){
        if(name == null || price == null || quantity == null || desc == null || category == null || status == null)
          return false;
        if(name.isEmpty()  || price.isEmpty()  || quantity.isEmpty() || desc.isEmpty() || category.isEmpty() || status.isEmpty())
            return false;
        return Integer.parseInt(quantity) >= 0 && Integer.parseInt(price) > 0;
    }
    protected static boolean isNotNULL(ItemInfo item){
        if(item == null)
            return false;
        return item.getName() != null && !item.getName().isEmpty() &&
                item.getDescription() != null &&
                item.getStatus() != null &&
                item.getQuantity() >= 0 &&
                item.getCategory() != null &&
                item.getPrice() > 0;
    }

}
