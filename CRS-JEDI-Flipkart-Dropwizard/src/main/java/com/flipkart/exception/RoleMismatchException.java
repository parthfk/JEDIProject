package com.flipkart.exception;

public class RoleMismatchException extends Exception{
    private String role;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param role
     */
    public RoleMismatchException(String role) {
        this.role = role;
    }

    /**
     * method that throws exception if wrong password is entered
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "No Role with name: " + role+
                ANSI_RESET;
    }
}
