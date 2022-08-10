package com.swiggy.model;

public  class Customer extends Person implements User, KYC, LivingThing// Inherits from [Object and Person] Class
{

    public Customer(String custName, String phone) // Overloaded Constructor
    {
        super(custName, phone);

        super.getName();
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
