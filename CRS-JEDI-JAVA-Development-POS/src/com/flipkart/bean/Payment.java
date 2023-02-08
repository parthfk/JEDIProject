package com.flipkart.bean;

import com.flipkart.constant.PaymentMode;

import java.util.Date;
import java.util.UUID;


/**
 * Payment Class- to store details about the payment made by the student
 * Contains variables and their getter setter functions
 */
public class Payment {
    public static final double AMOUNT = 10000;
    private String paymentId;
    private String studentId;
    private PaymentMode modeOfPayment;
    private Date transactionDate;

    /**
     * constructor
     * @param studentId
     */
    public Payment(String studentId){
        paymentId = UUID.randomUUID().toString();
        this.studentId = studentId;
        transactionDate = new Date();
    }
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

    public PaymentMode getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(PaymentMode modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
