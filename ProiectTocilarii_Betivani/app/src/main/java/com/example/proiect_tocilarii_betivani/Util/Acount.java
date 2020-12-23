package com.example.proiect_tocilarii_betivani.Util;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Acount implements Parcelable {
    private AccountType accountType;
    private String IBAN;
    private float balance;
    private float rate;
    private String createDate;
    private String expirationDate;
    private String holder;
    private String bank;


    public Acount() {}

    public Acount(AccountType accountType, String IBAN, float balance, float rate, String createDate, String expirationDate, String holder, String bank) {
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

    public String getCreateDate() { return createDate; }
    public void setCreateDate(String createDate) { this.createDate = createDate; }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public String getHolder() { return holder; }
    public void setHolder(String holder) { this.holder = holder; }

    public String getBank() { return bank; }
    public void setBank(String bank) { this.bank = bank; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accountType.toString());
        dest.writeString(IBAN);
        dest.writeFloat(balance);
        dest.writeFloat(rate);
        dest.writeString(createDate);
        dest.writeString(expirationDate);
        dest.writeString(holder);
        dest.writeString(bank);
    }

    protected Acount(Parcel in) {
        accountType = AccountType.valueOf(in.readString());
        IBAN = in.readString();
        balance = in.readFloat();
        rate = in.readFloat();
        createDate = in.readString();
        expirationDate = in.readString();
        holder = in.readString();
        bank = in.readString();
    }

    public static final Creator<Acount> CREATOR = new Creator<Acount>() {
        @Override
        public Acount createFromParcel(Parcel in) {
            return new Acount(in);
        }

        @Override
        public Acount[] newArray(int size) {
            return new Acount[size];
        }
    };
}
