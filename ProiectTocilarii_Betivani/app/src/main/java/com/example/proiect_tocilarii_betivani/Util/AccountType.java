package com.example.proiect_tocilarii_betivani.Util;

public enum AccountType  {
    Deposit(1),
    Credit(2);

    public final int value;

    AccountType(int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
