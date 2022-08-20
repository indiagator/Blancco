package com.swiggy.model;

import com.swiggy.exceptions.InsufficientFundsException;

public class Wallet {

     int balance; // uninitialized

    public Wallet(int balanceParam)
    {
        this.balance = balanceParam;
    }

    public int updateBalance(int updateValue)
    {
       this.balance += updateValue;
       return this.balance;
    }

    public boolean deductPayment(int paymentValue) throws InsufficientFundsException {

        if(this.balance >= paymentValue)
        {
            this.balance -= paymentValue;
            return true;
        }
        else
        {
            throw new InsufficientFundsException();
        }

    }

    public int getBalance() {

        return balance;
    }
}
