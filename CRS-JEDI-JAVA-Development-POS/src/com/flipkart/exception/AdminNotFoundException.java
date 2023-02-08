package com.flipkart.exception;

public class AdminNotFoundException extends Exception{
    private String emailId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * contructor
     * @param emailId
     */
    public AdminNotFoundException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws exception if admin try to log in with wrong credentials
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Admin with emailId: " + emailId + " is not found."+
                ANSI_RESET;
    }
}
