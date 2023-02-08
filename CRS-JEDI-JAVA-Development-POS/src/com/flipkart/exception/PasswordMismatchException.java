package com.flipkart.exception;

public class PasswordMismatchException extends Exception{
    private String userId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

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
        return ANSI_YELLOW+
                "Password mismatch of user with userId: " + userId+
                ANSI_RESET;
    }
}
