package com.flipkart.bean;

/**
 * PaymentNotification Class- to store details about the payment notification sent to student after fee payment
 * Contains variables and their getter setter functions
 */

public class PaymentNotification {
    private String studentId;
    private double paymentAmount;
    private String notificationId;
    private String message;

    /**
     * constructor
     * @param studentId
     * @param paymentAmount
     * @param paymentId
     * @param message
     */
    public PaymentNotification(String studentId,double paymentAmount,String paymentId,String message){
        this.studentId = studentId;
        this.paymentAmount = paymentAmount;
        this.notificationId = paymentId;
        this.message = message;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
