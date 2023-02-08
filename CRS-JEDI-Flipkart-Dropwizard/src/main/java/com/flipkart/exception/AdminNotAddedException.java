package com.flipkart.exception;

public class AdminNotAddedException extends Exception{
    private String emailId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param emailId
     */
    public AdminNotAddedException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws an exception if the new admin is not added successfully
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Admin with emailId: " + emailId + " is not added."+
                ANSI_RESET;
    }
}
