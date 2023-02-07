package com.flipkart.exception;

public class AdminNotFoundException extends Exception{
    private String adminId;

    /**
     * contructor
     * @param adminId
     */
    public AdminNotFoundException(String adminId) {
        super();
        this.adminId = adminId;
    }

    /**
     * method that throws exception if admin try to log in with wrong credentials
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Admin with adminId: " + adminId + " is not found.";
    }
}
