package com.swiggy.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Restaurant
{
    private final String name;
    private Location location;
   // private Dish[] menu;

    private Map<Integer, Dish> menu;

    public Restaurant(String name)
    {
        this.name = name;
        //this.menu = new Dish[5];
        menu = new HashMap<>(); // unlimited dishes
    }

    public Restaurant(String name, Map<Integer, Dish> menu, Location location)
    {
        this.name = name;
        this.menu = menu;
        this.location = location;
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

    public void setMenu(Map<Integer, Dish> menu) {
        this.menu = menu;
    }

    public Map<Integer,Dish> getMenu() {
        return menu;
    }
}

