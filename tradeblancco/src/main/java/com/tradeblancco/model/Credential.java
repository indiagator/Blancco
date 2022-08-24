package com.tradeblancco.model;

public class Credential {

    private final String username; // key
    private String password; // value

    public Credential(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
