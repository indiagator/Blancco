package com.swiggy.exceptions;

public class InsufficientFundsException extends Exception{

    @Override
    public String getMessage() {
        return "Your Wallet does not have sufficient Balance | Please update Wallet Balance";
    }
}
