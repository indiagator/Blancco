package com.tradeblancco.model;

public class Product {

    String hSCode;
    String name;
    String unit;

    Product(String hSCode, String name, String unit)
    {
        this.hSCode = hSCode;
        this.name = name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String gethSCode() {
        return hSCode;
    }
}
