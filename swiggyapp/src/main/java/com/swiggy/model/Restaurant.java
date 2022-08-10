package com.swiggy.model;

public class Restaurant
{
    private final String name;
    private Location location;
    private Dish[] menu;

    public Restaurant(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

