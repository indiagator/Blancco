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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getUsername().equals(user.getUsername())) return false;
        if (!getFullname().equals(user.getFullname())) return false;
        if (!getPhonenumber().equals(user.getPhonenumber())) return false;
        return getType().equals(user.getType());
    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getFullname().hashCode();
        result = 31 * result + getPhonenumber().hashCode();
        result = 31 * result + getType().hashCode();
        return result;
    }
}
