package com.tradeblancco.model;

public class Admin extends User
{
    public Admin(String username, String fullname, String phonenumber)
    {
        super(username, fullname, phonenumber,"admin");
        //super.setType("buyer");
    }
}
