package com.flipkart.exception;

public class PaymentNotFoundException extends Exception{
    private String paymentId;
    private String studentId;

    /**
     * constructor
     * @param id
     * @param studentId
     */
    public PaymentNotFoundException(String id,String studentId) {
        this.paymentId = id;
        this.studentId=studentId;
    }

    /**
     * method that throws exception if payment ID is not found
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Payment with paymentId: " + paymentId  +" and studentId: "+ studentId + " not found!";
    }
}
