package com.mycornership.expanseapp.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CurrencyRate {

    @NonNull
    @PrimaryKey
    private String symbol;

    private  double rate;

    public CurrencyRate(String symbol, double rate) {
        this.symbol = symbol;
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
