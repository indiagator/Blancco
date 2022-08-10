package com.swiggy.model;

public class Vendor extends Person implements User,KYC, LivingThing{

    private String name; // Data Hidden
    public Vendor(String name, String phone)
    {
        super(name, phone);
    }

    public String toString()
    {
        return "Vendor's name is "+this.getName()+" and phone number is "+this.getPhone();
    }

    public void setThisName(String name) {
        this.name = name; // Data Hiding
    }

    public void setSuperName(String name) {
        super.name = name;
    }

    public String getThisName() {
        return this.name;
    }

    public String getSuperName() {
        return super.name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }

    @Override
    public void updateKYC() {

    }

    @Override
    public String sayHello() {
        return "Hello I'm a Vendor! I cook great Food";
    }
}
