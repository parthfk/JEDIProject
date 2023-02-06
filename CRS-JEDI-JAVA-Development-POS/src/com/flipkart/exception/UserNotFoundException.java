package com.flipkart.exception;

public class UserNotFoundException extends Exception{

    private String userId;

    public UserNotFoundException(String userId) {
        super();
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return "User with userId: " + userId + " is not found.";
    }
}
