package com.swiggy.model;

public class LivingThingBuilder
{
   public static LivingThing build(String lvtype)
    {
        LivingThing lv = null;

        if(lvtype.equals("customer")) // this is an example of a design pattern
        {
            lv = new Customer("default","default","","");
        }
        else if (lvtype.equals("person"))
        {
            lv = new Person("default","default");
        }
        else if (lvtype.equals("vendor"))
        {
            lv = new Vendor("default","default");
        }
        return lv;
    }
}
