package com.flipkart.exception;

public class AdminNotAddedException extends Exception{
    private String emailId;

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
        return "Admin with emailId: " + emailId + " is not added.";
    }
}
