package com.swiggy.model;

 public class Wallet {

     int balance; // uninitialized

    public Wallet(int balanceParam)
    {
        this.balance = balanceParam;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {

        return balance;
    }
}
