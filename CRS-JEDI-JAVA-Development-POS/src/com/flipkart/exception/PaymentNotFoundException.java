package com.flipkart.exception;

public class PaymentNotFoundException extends Exception{
    private String paymentId;
    private String studentId;

    public PaymentNotFoundException(String id,String studentId) {
        this.paymentId = id;
        this.studentId=studentId;
    }
    @Override
    public String getMessage() {
        return "Payment with paymentId: " + paymentId  +" and studentId: "+ studentId + " not found!";
    }
}
