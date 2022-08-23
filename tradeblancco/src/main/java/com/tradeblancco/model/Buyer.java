package com.tradeblancco.model;

public class Buyer
{
    private String phonenumber;
    private String name;

    public Buyer(String phonenumber, String name)
    {

        this.phonenumber = phonenumber;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
