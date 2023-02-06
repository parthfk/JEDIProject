package com.flipkart.dao;
import com.flipkart.bean.Student;

public interface PaymentDAO {

    /**
     * @param studentId
     * @param paymentAmount
     * @param paymentId
     * @param message
     */
    public void sendNotification(String studentId,double paymentAmount,String paymentId,String message);

    /**
     * @param student
     */
    public void payCreditCard(Student student);

    /**
     * @param student
     */
    public void payDebitCard(Student student);

    /**
     * @param student
     */
    public void payUPI(Student student);

    /**
     * @param student
     */
    public void payNetBanking(Student student);

    /**
     * @param student
     */
    public void payCash(Student student);

    /**
     * @param student
     */
    public void payCheque(Student student);

    /**
     * @return
     */
    public String generatePaymentId();

    /**
     * @param student 
     * @return
     */
    public boolean paymentApproved(Student student);

}
