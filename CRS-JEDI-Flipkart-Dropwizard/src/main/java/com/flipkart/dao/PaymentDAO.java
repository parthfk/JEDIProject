package com.flipkart.dao;
import com.flipkart.bean.Student;

public interface PaymentDAO {

    /**
     * method that send the payment notification after fee is paid
     * @param studentId
     * @param paymentAmount
     * @param message
     */
    public void sendNotification(String studentId,double paymentAmount,String message);

    /**
     * method that allows student to pay via credit card
     * @param student
     */
    public boolean payCreditCard(Student student);

    /**
     * method that allows student to pay via debit card
     * @param student
     */
    public boolean payDebitCard(Student student);

    /**
     * method that allows student to pay via UPI
     * @param student
     */
    public boolean payUPI(String studentId, String upiId);

    /**
     * method that allows student to pay via Net banking
     * @param student
     */
    public boolean payNetBanking(Student student);

    /**
     * method that allows student to pay via cash
     * @param student
     */
    public boolean payCash(Student student);

    /**
     * method that allows student to pay via cheque
     * @param student
     */
    public boolean payCheque(Student student);

    /**
     * method that generates the payment id
     * @return
     */
    public String generatePaymentId();

    /**
     * method that  toggles the fee paid attribute after fee is paid
     * @param student 
     * @return
     */
    public boolean paymentApproved(Student student);

    /**
     * Helper function used in Payment Dao implementation to avoid redundancy of code
     * @param paymentId
     * @param student
     * @param modeOfPayment
     */

}
