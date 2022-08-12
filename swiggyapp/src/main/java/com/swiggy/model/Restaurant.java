package com.swiggy.model;

public class Restaurant
{
    private final String name;
    private Location location;
    private Dish[] menu;

    public Restaurant(String name)
    {
        this.name = name;
        this.menu = new Dish[5];
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setMenu(Dish[] menu) {
        this.menu = menu;
    }

    public Dish[] getMenu() {
        return menu;
    }
}

