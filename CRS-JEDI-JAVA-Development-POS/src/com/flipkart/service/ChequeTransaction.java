package com.flipkart.service;

public class ChequeTransaction {
    private String chequeNumber;
    
    private String bankName;

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void paymentApproved(){

    }
}
