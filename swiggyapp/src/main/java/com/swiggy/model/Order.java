package com.swiggy.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Order
{
    private String id; //generated internally
    private Customer customer;
    private String restroName;
    private Map<Dish,Integer> dishMap;
    private LocalDateTime timeStamp;
    private String status; //confirmed, processing, delivering, delivered, cancelled, deleted, created


    public Order(Customer customer, String restroName )
    {
        this.customer = customer;
        this.restroName = restroName;
        this.id = new Integer(((Double)(Math.random()*10000)).intValue()).toString() +
                new Integer(((Double)(Math.random()*10000)).intValue()).toString();
        this.timeStamp = LocalDateTime.now();
        this.status = "created";
        this.dishMap = null;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getRestroName() {
        return restroName;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public Map<Dish, Integer> getDishMap() {
        return this.dishMap;
    }

    public void setDishMap(Map<Dish, Integer> dishMap) {
        this.dishMap = dishMap;
    }

    public void setStatusConfirmed()
    {
        this.status = "confirmed";
    }

    public void setStatusProcessing()
    {
        this.status = "processing";
    }

    public void setStatusDelivering()
    {
        this.status = "delivering";
    }

    public void setStatusDelivered()
    {
        this.status = "delivered";
    }

    public void setStatusCancelled()
    {
        this.status = "cancelled";
    }

    public void setStatusDeleted()
    {
        this.status = "deleted";
    }

    public int getOrderAmnt()
    {
        int orderAmnt = 0;

        //Optional<Integer> tempOrderAmnt = (this.dishMap != null)?(value if true) ;(null);

        Optional<Integer> tempOrderAmnt = (this.dishMap.entrySet().stream().map(entry -> entry.getKey().getPrice() * entry.getValue().intValue() ).reduce(Integer::sum));

        if (tempOrderAmnt.isPresent())
        {
            orderAmnt = tempOrderAmnt.get().intValue();
        }

       // if(this.dishMap != null)
       // {

          //  for(Map.Entry<Dish, Integer> entry : this.dishMap.entrySet())
          //  {
          //      orderAmnt += entry.getKey().getPrice() * entry.getValue().intValue(); // price X qty

         //   }
        //}
      //  else
      //  {
      //      throw new RuntimeException();
      //  }

        return orderAmnt;

    }

    //private int orderAmnt; // This can be calculated from the dish data so it's redundant
    // private DelGuy delGuy;
    // private Review review;
    // private String comments;
}
