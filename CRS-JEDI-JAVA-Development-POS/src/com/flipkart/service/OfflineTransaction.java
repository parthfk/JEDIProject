package com.flipkart.service;

public class OfflineTransaction extends Payment{
    private String cashierName;

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }
}
