package com.swiggy.model;

public  class Customer extends Person implements User, KYC, LivingThing// Inherits from [Object and Person] Class
{

    private final Wallet wallet;

    private String username;
    private String password;

    public Customer(String name, String phone, String username, String password) // Overloaded Constructor
    {
        super(name, phone);

        super.getName();

        this.wallet = new Wallet(0);
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public String toString()
    {
        return "Customer's name is "+this.getName()+" and phone number is "+this.getPhone();
    }


    @Override
    public void login() {
        //logic here
    }

    @Override
    public void logout() {
    //logic here
    }

    @Override
    public void updateKYC() {

    }

    @Override
    public String sayHello() {
        return "Hello I'm a Customer!";
    }
}
