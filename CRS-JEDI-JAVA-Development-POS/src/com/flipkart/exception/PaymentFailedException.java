package com.flipkart.exception;

public class PaymentFailedException extends Exception{
    private String studentId;

    public PaymentFailedException( String studentId) {

        this.studentId=studentId;
    }
    @Override
    public String getMessage() {
        return "Payment with studentId: "+ studentId + " not found!";
    }
}
