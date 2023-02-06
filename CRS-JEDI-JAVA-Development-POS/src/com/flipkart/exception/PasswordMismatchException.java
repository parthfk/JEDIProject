package com.flipkart.exception;

public class PasswordMismatchException extends Exception{
    private String userId;


    public PasswordMismatchException(String userId) {
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return "Password mismatch of user with userId: " + userId;
    }
}
