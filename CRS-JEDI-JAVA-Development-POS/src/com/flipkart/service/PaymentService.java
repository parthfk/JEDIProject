package com.flipkart.service;

public interface PaymentService {
    public double calculateAmount();

    public boolean paymentApproved();

    public void payCreditCard();
    public void payDebitCard();
    public void payUPI();
    public void payNetBanking();
    public void payCash();
    public void payCheque();

    public void sendNotification(String studentId,double paymentAmount,String notificationId,String message);
}
