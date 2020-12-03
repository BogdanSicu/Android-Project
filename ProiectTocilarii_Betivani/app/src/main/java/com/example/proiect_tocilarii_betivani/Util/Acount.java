package com.example.proiect_tocilarii_betivani.Util;

import java.util.Date;

public class Acount {
    private AccountType accountType;
    private String IBAN;
    private float balance;
    private float rate;
    private Date createDate;
    private Date expirationDate;
    private String holder;
    private String bank;


    public Acount() {}

    public Acount(AccountType accountType, String IBAN, float balance, float rate, Date createDate, Date expirationDate, String holder, String bank) {
        this.accountType = accountType;
        this.IBAN = IBAN;
        this.balance = balance;
        this.rate = rate;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.holder = holder;
        this.bank = bank;
    }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public String getIBAN() { return IBAN; }
    public void setIBAN(String IBAN) { this.IBAN = IBAN; }

    public float getBalance() { return balance; }
    public void setBalance(float balance) { this.balance = balance; }

    public float getRate() { return rate; }
    public void setRate(float rate) { this.rate = rate; }

    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }

    public Date getExpirationDate() { return expirationDate; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }

    public String getHolder() { return holder; }
    public void setHolder(String holder) { this.holder = holder; }

    public String getBank() { return bank; }
    public void setBank(String bank) { this.bank = bank; }
}
