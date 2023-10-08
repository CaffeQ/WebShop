package com.example.webshop.bo;

import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Handles operations related to Item objects such as fetching, removing, editing, and adding items.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class ItemHandler {

    /**
     * Retrieves all items from the database and returns them as a collection of ItemInfo objects.
     *
     * @return Collection<ItemInfo> List of ItemInfo objects.
     */
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

    /**
     * Retrieves an item by its name from the database and returns it as an ItemInfo object.
     *
     * @param itemName Name of the item to fetch.
     * @return ItemInfo  object with details of the fetched item.
     */
    public static ItemInfo getItemByName(String itemName){
        Item itemDB = Item.getItemIdByName(itemName);
        return new ItemInfo(itemDB.getName(), itemDB.getPrice(),itemDB.getDescription(), itemDB.getQuantity(), itemDB.getCategory(), itemDB.getStatus());
    }

    /**
     * Converts an Item object to an ItemInfo object.
     *
     * @param item Item object to convert.
     * @return ItemInfo Converted ItemInfo object.
     */
    protected static ItemInfo itemToItemInfo(Item item){
        return new ItemInfo(item.getName(), item.getPrice(), item.getDescription(), item.getQuantity(), item.getCategory(), item.getStatus());
    }

    /**
     * Removes an item based on the parameter received from HttpServletRequest.
     *
     * @param request HttpServletRequest containing the parameter "removeItem".
     * @return boolean True if the item was successfully removed, otherwise false.
     */
    public static boolean removeItem(HttpServletRequest request)  {
        String removeItem = request.getParameter("removeItem");
        Item item = Item.getItemIdByName(removeItem);
        return Item.removeItem(item);
    }

    /**
     * Edits an existing item based on the parameters received from HttpServletRequest.
     *
     * @param request HttpServletRequest containing necessary parameters for editing.
     * @return boolean True if the item was successfully edited, otherwise false.
     */
    public static boolean editItem(HttpServletRequest request){
        String previousName = request.getParameter("previousName");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String desc = request.getParameter("description");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        int itemID = Item.getItemIdByName(previousName).getId();
        if(!checkItemParameters(name,price,quantity,desc,category,status))
            return false;
        return Item.editItem(new Item(itemID,name,Integer.parseInt(price), desc, Integer.parseInt(quantity), category,status, true));
    }

    /**
     * Adds a new item based on the parameters received from HttpServletRequest.
     *
     * @param request HttpServletRequest containing necessary parameters for adding.
     * @return boolean True if the item was successfully added, otherwise false.
     * @throws SQLException In case of SQL errors.
     */
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

        Item item = new Item(0, name, Integer.parseInt(price), desc, Integer.parseInt(quantity), category, status, true);

        return Item.createItem(item);
    }

    /**
     * Validates the parameters for an Item object.
     *
     * @param name Name of the item.
     * @param price Price of the item.
     * @param quantity Quantity of the item.
     * @param desc Description of the item.
     * @param category Category of the item.
     * @param status Status of the item.
     * @return boolean True if all parameters are valid, otherwise false.
     */
    private static boolean checkItemParameters(String name, String price, String quantity, String desc, String category, String status){
        if(name == null || price == null || quantity == null || desc == null || category == null || status == null)
          return false;
        if(name.isEmpty()  || price.isEmpty()  || quantity.isEmpty() || desc.isEmpty() || category.isEmpty() || status.isEmpty())
            return false;
        return Integer.parseInt(quantity) >= 0 && Integer.parseInt(price) > 0;
    }


}
