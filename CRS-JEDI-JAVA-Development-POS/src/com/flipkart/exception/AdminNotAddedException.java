package com.flipkart.exception;

public class AdminNotAddedException extends Exception{
    private String adminId;

    public AdminNotAddedException(String adminId) {
        super();
        this.adminId = adminId;
    }

    @Override
    public String getMessage() {
        return "Admin with adminId: " + adminId + " is not added.";
    }
}
