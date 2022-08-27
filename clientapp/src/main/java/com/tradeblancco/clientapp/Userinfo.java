package com.tradeblancco.clientapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class Userinfo {
    @Id
    @Column(name = "username", nullable = false, length = 30)
    private String id;

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;

    @Column(name = "phonenumber", nullable = false, length = 15)
    private String phonenumber;

    @Column(name = "type", nullable = false, length = 15)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}