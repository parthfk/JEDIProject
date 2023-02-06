package com.flipkart.dao;
import com.flipkart.bean.Student;

public interface PaymentDAO {

    public void sendNotification(String studentId,double paymentAmount,String paymentId,String message);
    public void payCreditCard(Student student);
    public void payDebitCard();
    public void payUPI();
    public void payNetBanking();
    public void payCash();
    public void payCheque();
    public String generatePaymentId();
    public boolean paymentApproved(Student student);

}
