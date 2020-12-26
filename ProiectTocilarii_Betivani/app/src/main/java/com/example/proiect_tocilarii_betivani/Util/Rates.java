package com.example.proiect_tocilarii_betivani.Util;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rates")
public class Rates {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    @ColumnInfo(name = "bank_name")
    private String bankName;
    @ColumnInfo(name = "credit_rate")
    private float creditRate;
    @ColumnInfo(name = "debit_rate")
    private float depositRate;

    @Ignore
    public Rates() {}

    @Ignore
    public Rates(String bankName, float creditRate, float depositRate) {
        this.bankName = bankName;
        this.creditRate = creditRate;
        this.depositRate = depositRate;
    }

    public Rates(long id, String bankName, float creditRate, float depositRate) {
        this.id = id;
        this.bankName = bankName;
        this.creditRate = creditRate;
        this.depositRate = depositRate;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public float getCreditRate() { return creditRate; }
    public void setCreditRate(float creditRate) { this.creditRate = creditRate; }

    public float getDepositRate() { return depositRate; }
    public void setDepositRate(float depositRate) { this.depositRate = depositRate; }
}
