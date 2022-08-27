package com.codeaug.testblanccospring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "phonenumber", nullable = false, length = 10)
    private String phonenumber;

    @Column(name = "name", length = 80)
    private String name;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String id) {
        this.phonenumber = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}