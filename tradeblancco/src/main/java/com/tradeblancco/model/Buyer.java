package com.tradeblancco.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Buyer extends User
{

    private List<Product> productList;
    public Buyer(String username, String fullname, String phonenumber)
    {
        super(username, fullname, phonenumber,"buyer");
        //super.setType("buyer");

        this.productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product)
    {
        this.productList.add(product);
    }

    public void removeProduct(String productName)
    {

        Optional<Product> tempProduct;

        if(  (tempProduct =  this.productList.stream().filter(product -> product.getName().equals(productName)).findAny()).isPresent() )
        {
            this.productList.remove(tempProduct.get());
        }
    }


}
