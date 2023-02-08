package com.flipkart.exception;

public class PaymentFailedException extends Exception {
    private String studentId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

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
        return ANSI_YELLOW+
                "Payment with studentId: "+ studentId + " not found!"+
                ANSI_RESET;
    }
}
