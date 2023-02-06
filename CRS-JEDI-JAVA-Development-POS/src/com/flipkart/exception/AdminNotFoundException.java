package com.flipkart.exception;

public class AdminNotFoundException extends Exception{
    private String adminId;

    public AdminNotFoundException(String adminId) {
        super();
        this.adminId = adminId;
    }

    @Override
    public String getMessage() {
        return "Admin with adminId: " + adminId + " is not found.";
    }
}
