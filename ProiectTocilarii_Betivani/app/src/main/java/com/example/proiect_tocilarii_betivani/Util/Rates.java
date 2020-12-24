package com.example.proiect_tocilarii_betivani.Util;

public class Rates {
    private String bankName;
    private float creditRate;
    private float depositRate;

    public Rates() {}

    public Rates(String bankName, float creditRate, float depositRate) {
        this.bankName = bankName;
        this.creditRate = creditRate;
        this.depositRate = depositRate;
    }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public float getCreditRate() { return creditRate; }
    public void setCreditRate(float creditRate) { this.creditRate = creditRate; }

    public float getDepositRate() { return depositRate; }
    public void setDepositRate(float depositRate) { this.depositRate = depositRate; }
}
