package com.tradeblancco.model;

public class Credential {

    private String username;
    private String password;

    public Credential(String username, String password)
    {
        this.username = username;
        this.username = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
