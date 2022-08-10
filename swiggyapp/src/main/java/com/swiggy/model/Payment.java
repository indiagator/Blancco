package com.swiggy.model;

public class Payment {


    public int incrementInt(int x)
    {
        return ++x;
    }

    public Wallet incrementWalletBalance(Wallet wallet)
        {
            wallet.setBalance(wallet.getBalance() + 150);
            return wallet;
        }

}
