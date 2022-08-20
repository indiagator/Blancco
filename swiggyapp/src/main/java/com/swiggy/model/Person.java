package com.swiggy.model;

public class Person implements LivingThing{

    protected String name;
     protected String phone;

     private Location location;

    public Person(String name, String phone)
    {
        this.name = name;
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString()
    {
        return "Person's name is "+this.getName()+" and phone number is "+this.getPhone();
    }

    @Override
    public String sayHello() {
        return "Hello I'm just a Person!";
    }
}
