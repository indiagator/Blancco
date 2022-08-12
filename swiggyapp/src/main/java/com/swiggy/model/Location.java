package com.swiggy.model;

public class Location {

    private int lat;
    private int longi;

    private final String  name;

    public Location(String name, int lat, int longi)
    {
        this.name = name;
        this.lat = lat;
        this.longi = longi;
    }

    public int getLat() {
        return lat;
    }

    public int getLongi() {
        return longi;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLongi(int longi) {
        this.longi = longi;
    }

    public String getName() {
        return name;
    }
}

