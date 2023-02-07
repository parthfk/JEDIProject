package com.flipkart.exception;

public class PaymentFailedException extends Exception {
    private String studentId;

    /**
     * constructor
     * @param studentId
     */
    public PaymentFailedException( String studentId) {

        this.studentId=studentId;
    }

    /**
     * method that throws exception if payment fails
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Payment with studentId: "+ studentId + " not found!";
    }
}
