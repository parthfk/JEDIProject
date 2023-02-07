package com.flipkart.exception;

public class AdminNotAddedException extends Exception{
    private String adminId;

    /**
     * constructor
     * @param adminId
     */
    public AdminNotAddedException(String adminId) {
        super();
        this.adminId = adminId;
    }

    /**
     * method that throws an exception if the new admin is not added successfully
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Admin with adminId: " + adminId + " is not added.";
    }
}
