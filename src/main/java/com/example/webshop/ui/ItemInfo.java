package com.example.webshop.ui;

public class ItemInfo {

    private final String name;
    private final int price;
    private final String description;
    private final int quantity;
    private final String category;
    private final String status;

    public ItemInfo(String name, int price, String description, int quantity, String category, String status) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public int getPrice() {return price;}

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}