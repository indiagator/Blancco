package com.tradeblancco.model;

public class Buyer extends User
{
    public Buyer(String username, String fullname, String phonenumber)
    {
        super(username, fullname, phonenumber,"buyer");
        //super.setType("buyer");
    }
}
