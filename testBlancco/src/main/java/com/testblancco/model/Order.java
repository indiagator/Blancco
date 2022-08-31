package com.testblancco.model;



public class Order {


    private Kitchen kitchen;
    private Customer customer;
    private Dish dish;

    public Order(Kitchen kitchen, Customer customer, Dish dish)
    {

        this.kitchen = kitchen;
        this.customer = customer;
        this.dish = dish;

    }

    public boolean deliverOrder()
    {

        if(this.kitchen.getName().equals(this.customer.getName()))
        {
            return true;
        } else if (this.dish.getName().equals(this.customer.getName()))
        {
          return false;
        }
        else
        {
            return true;
        }

    }


}
