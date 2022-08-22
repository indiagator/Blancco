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

    public void printMenu() {

        for (int i = 1; i < (this.getMenu().size()+1);i++ )
        {
            System.out.print("    "+i+". "+this.getMenu().get(new Integer(i)).getName()+" ");
            System.out.print("INR"+this.getMenu().get(new Integer(i)).getPrice()+" ");
            if(this.getMenu().get(new Integer(i)).getType() == 0)
            {System.out.print("Veg \n");}
            else if (this.getMenu().get(new Integer(i)).getType() == 1) {System.out.print("Non Veg \n");}
        }
    }
}

