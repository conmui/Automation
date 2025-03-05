package com.conmui;

public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private int total;

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 1;
        this.total = price * quantity;
    }

    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotal() {
        return total;
    }
}
