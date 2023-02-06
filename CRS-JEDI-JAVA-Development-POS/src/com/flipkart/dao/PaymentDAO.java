package com.flipkart.dao;
import com.flipkart.bean.Student;

public interface PaymentDAO {

    public void sendNotification(String studentId,double paymentAmount,String paymentId,String message);
    public void payCreditCard(Student student);
    public void payDebitCard(Student student);
    public void payUPI(Student student);
    public void payNetBanking(Student student);
    public void payCash(Student student);
    public void payCheque(Student student);
    public String generatePaymentId();
    public boolean paymentApproved(Student student);

}
