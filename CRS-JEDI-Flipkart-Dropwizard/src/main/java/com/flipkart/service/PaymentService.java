package com.flipkart.service;

import com.flipkart.bean.Student;

public interface PaymentService {
    /**
     * Method which tells the amount to be paid by student at the end of registration.
     * As of now, it is kept a constant of 10,000/-
     * @return amount
     */

    double calculateAmount();

    /**
     * Method which tells the status of payment if it has been completed successfully.
     * @paramsStudent object
     * @return boolean
     */
    boolean paymentApproved(Student student);

    /**
     * Method which collects credit card details if a student wants to pay by credit card
     */
    void payCreditCard();
    /**
     * Method which collects debit card details if a student wants to pay by debit card
     */
    void payDebitCard();
    /**
     * Method which collects UPI details if a student wants to pay by UPI
     */
//    void payUPI(String studentId, String upiId);
    /**
     * Method which collects net banking details if a student wants to pay net banking.
     */
    void payNetBanking();
    /**
     * Method which collects cash details if a student wants to pay by cash
     */
    void payCash();
    /**
     * Method which collects cheque details if a student wants to pay by cheque
     */
    void payCheque();

    /**
     * Method which sends a payment notification to student after the payment is successfully completed by the student
     * @param studentId
     * @param paymentAmount
     * @param paymentId
     * @param message
     */
    void sendNotification(String studentId,double paymentAmount,String paymentId,String message);
}
