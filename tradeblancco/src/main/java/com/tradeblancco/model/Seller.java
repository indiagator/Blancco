package com.tradeblancco.model;

public class Seller extends User
{

    public Seller(String username, String fullname, String phonenumber)
    {
        super(username, fullname, phonenumber,"seller");
        //super.setType("buyer");
    }


}
