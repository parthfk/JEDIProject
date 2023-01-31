package com.flipkart.bean;

import java.util.Date;

public class Payment {
    private String paymentId;
    private String studentId;
    private double amount;
    private int modeOfPayment;
    private Date transactionDate;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(int modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
