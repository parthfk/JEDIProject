package com.flipkart.exception;

public class AdminAlreadyExistException extends Exception{
    private String emailId;

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
        return "Admin with emailId: " + emailId + " already exist.";
    }
}
