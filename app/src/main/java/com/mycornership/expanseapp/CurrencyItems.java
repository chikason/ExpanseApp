package com.mycornership.expanseapp;

public class CurrencyItems {
    private String symbol;
    private int icon;

    public CurrencyItems(String symbol, int icon) {
        this.symbol = symbol;
        this.icon = icon;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
