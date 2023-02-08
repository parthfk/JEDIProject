package com.flipkart.exception;

public class PaymentNotFoundException extends Exception{
    private String paymentId;
    private String studentId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

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
        return ANSI_YELLOW+
                "Payment with paymentId: " + paymentId  +" and studentId: "+ studentId + " not found!"+
                ANSI_RESET;
    }
}
