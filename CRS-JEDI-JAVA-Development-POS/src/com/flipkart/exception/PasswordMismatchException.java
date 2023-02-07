package com.flipkart.exception;

public class PasswordMismatchException extends Exception{
    private String userId;

    /**
     * constructor
     * @param userId
     */
    public PasswordMismatchException(String userId) {
        this.userId = userId;
    }

    /**
     * method that throws exception if wrong password is entered
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Password mismatch of user with userId: " + userId;
    }
}
