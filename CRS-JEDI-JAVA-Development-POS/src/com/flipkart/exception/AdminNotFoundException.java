package com.flipkart.exception;

public class AdminNotFoundException extends Exception{
    private String emailId;

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
        return "Admin with emailId: " + emailId + " is not found.";
    }
}
