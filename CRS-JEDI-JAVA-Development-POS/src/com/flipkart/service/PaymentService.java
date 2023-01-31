package com.flipkart.service;

public interface PaymentService {
    public double calculateAmount();

    public void paymentApproved();

    public int sendNotification();
}
