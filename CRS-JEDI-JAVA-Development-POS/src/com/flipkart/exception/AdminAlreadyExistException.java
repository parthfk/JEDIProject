package com.flipkart.exception;

public class AdminAlreadyExistException extends Exception{
    private String emailId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param emailId
     */
    public AdminAlreadyExistException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws exception if we try to add an admin that already exists
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Admin with emailId: " + emailId + " already exist."+
                ANSI_RESET;
    }
}
