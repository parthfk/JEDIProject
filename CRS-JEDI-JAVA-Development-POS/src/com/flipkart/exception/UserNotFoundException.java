package com.flipkart.exception;

public class UserNotFoundException extends Exception{

    private String emailId;

    /**
     * constructor
     * @param emailId
     */
    public UserNotFoundException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws exception if any user enters wrong credentials while logging in
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "User with userId: " + emailId + " is not found.";
    }
}
