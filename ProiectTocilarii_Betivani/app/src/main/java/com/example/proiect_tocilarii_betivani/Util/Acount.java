package com.example.proiect_tocilarii_betivani.Util;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//marcam clasa pentru a sti baza de date ca aceasta clasa este TABELA
@Entity(tableName = "accounts")
public class Acount implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    @ColumnInfo(name="account_type")
    private AccountType accountType;
    @ColumnInfo(name="iban")
    private String IBAN;
    @ColumnInfo(name="balance")
    private float balance;
    @ColumnInfo(name="rate")
    private float rate;
    @ColumnInfo(name="create_date")
    private String createDate;
    @ColumnInfo(name="period")
    private int period;
    @ColumnInfo(name="holder")
    private String holder;
    @ColumnInfo(name="bank")
    private String bank;


    @Ignore
    public Acount() {}

    @Ignore
    public Acount(AccountType accountType, String IBAN, float balance, float rate, String createDate, int period, String holder, String bank) {
        this.accountType = accountType;
        this.IBAN = IBAN;
        this.balance = balance;
        this.rate = rate;
        this.createDate = createDate;
        this.period = period;
        this.holder = holder;
        this.bank = bank;
    }

    public Acount(long id, AccountType accountType, String IBAN, float balance, float rate, String createDate, int period, String holder, String bank) {
        this.id = id;
        this.accountType = accountType;
        this.IBAN = IBAN;
        this.balance = balance;
        this.rate = rate;
        this.createDate = createDate;
        this.period = period;
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

    public int getPeriod() { return period; }
    public void setPeriod(int period) { this.period = period; }

    public String getHolder() { return holder; }
    public void setHolder(String holder) { this.holder = holder; }

    public String getBank() { return bank; }
    public void setBank(String bank) { this.bank = bank; }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(accountType.toString());
        dest.writeString(IBAN);
        dest.writeFloat(balance);
        dest.writeFloat(rate);
        dest.writeString(createDate);
        dest.writeInt(period);
        dest.writeString(holder);
        dest.writeString(bank);
    }

    protected Acount(Parcel in) {
        id = in.readLong();
        accountType = AccountType.valueOf(in.readString());
        IBAN = in.readString();
        balance = in.readFloat();
        rate = in.readFloat();
        createDate = in.readString();
        period = in.readInt();
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
