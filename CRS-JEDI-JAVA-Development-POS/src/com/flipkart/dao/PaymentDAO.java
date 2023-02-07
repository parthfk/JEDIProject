package com.flipkart.dao;
import com.flipkart.bean.Student;

public interface PaymentDAO {

    public boolean sendNotification(String studentId,String paymentId);
    public String  payCreditCard(Student student,double paymentAmount,String message);
    public String payDebitCard(Student student,double paymentAmount,String message);
    public String payUPI(Student student,double paymentAmount,String message);
    public String payNetBanking(Student student,double paymentAmount,String message);
    public String payCash(Student student,double paymentAmount,String message);
    public String payCheque(Student student,double paymentAmount,String message);
    public String generatePaymentId();
    public boolean paymentApproved(Student student);

}
