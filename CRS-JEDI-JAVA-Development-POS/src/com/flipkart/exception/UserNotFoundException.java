package com.flipkart.exception;

public class UserNotFoundException extends Exception{

    private String userId;

    /**
     * constructor
     * @param userId
     */
    public UserNotFoundException(String userId) {
        super();
        this.userId = userId;
    }

    /**
     * method that throws exception if any user enters wrong credentials while logging in
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "User with userId: " + userId + " is not found.";
    }
}
