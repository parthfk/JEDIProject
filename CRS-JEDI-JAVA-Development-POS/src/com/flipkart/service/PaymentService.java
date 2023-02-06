package com.flipkart.service;

import com.flipkart.bean.Student;

public interface PaymentService {
    public double calculateAmount();

    public boolean paymentApproved(Student student);

    public void payCreditCard();
    public void payDebitCard();
    public void payUPI();
    public void payNetBanking();
    public void payCash();
    public void payCheque();

    public void sendNotification(String studentId,double paymentAmount,String paymentId,String message);
}
