package com.swiggy.model;

public  class Dish {

    private String name;
    private int price;

    private String restroName;

    protected int type; // veg dish - 0, non-veg dish is 1, ...

    public Dish(String name, int price, String restroName, int type)
    {
        this.name = name;
        this.price = price;
        this.restroName = restroName;
        this.type = type;
    }

    public  String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getType() {
        return type;
    }

    public String getRestroName() {
        return restroName;
    }
}
