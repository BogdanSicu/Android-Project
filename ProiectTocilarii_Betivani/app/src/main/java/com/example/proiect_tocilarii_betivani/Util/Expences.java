package com.example.proiect_tocilarii_betivani.Util;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Expences implements Serializable {
    private String BankName;
    private float CreditCommission;
    private float CreditRate;
    private float DepositCommission;
    private float DepositRate;
    private float MinCreditAmount;
    private float MinCreditPeriod;
    private float MinDepositAmount;
    private float MinDepositPeriod;

    public Expences() { }

    public Expences(String bankName, float creditCommission, float creditRate, float depositCommission,
                    float depositRate, float minCreditAmount, float minCreditPeriod, float minDepositAmount, float minDepositPeriod) {
        BankName = bankName;
        CreditCommission = creditCommission;
        CreditRate = creditRate;
        DepositCommission = depositCommission;
        DepositRate = depositRate;
        MinCreditAmount = minCreditAmount;
        MinCreditPeriod = minCreditPeriod;
        MinDepositAmount = minDepositAmount;
        MinDepositPeriod = minDepositPeriod;
    }

    public String getBankName() { return BankName; }
    public void setBankName(String bankName) { BankName = bankName; }

    public float getCreditCommission() { return CreditCommission; }
    public void setCreditCommission(float creditCommission) { CreditCommission = creditCommission; }

    public float getCreditRate() { return CreditRate; }
    public void setCreditRate(float creditRate) { CreditRate = creditRate; }

    public float getDepositCommission() { return DepositCommission; }
    public void setDepositCommission(float depositCommission) { DepositCommission = depositCommission; }

    public float getDepositRate() { return DepositRate; }
    public void setDepositRate(float depositRate) { DepositRate = depositRate; }

    public float getMinCreditAmount() { return MinCreditAmount; }
    public void setMinCreditAmount(float minCreditAmount) { MinCreditAmount = minCreditAmount; }

    public float getMinCreditPeriod() { return MinCreditPeriod; }
    public void setMinCreditPeriod(float minCreditPeriod) { MinCreditPeriod = minCreditPeriod; }

    public float getMinDepositAmount() { return MinDepositAmount; }
    public void setMinDepositAmount(float minDepositAmount) { MinDepositAmount = minDepositAmount; }

    public float getMinDepositPeriod() { return MinDepositPeriod; }
    public void setMinDepositPeriod(float minDepositPeriod) { MinDepositPeriod = minDepositPeriod; }

    @NonNull
    @Override
    public String toString() {
        return this.BankName;
    }
}
