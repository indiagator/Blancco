package com.tradeblancco.model;

public class User {

    private final String username;

    private String fullname;
    private String phonenumber;

    private String type; // buyer, seller, admin, (log) ...

    public User(String username, String fullname, String phonenumber, String type)
    {
        this.username = username;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.type = type;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }
}
