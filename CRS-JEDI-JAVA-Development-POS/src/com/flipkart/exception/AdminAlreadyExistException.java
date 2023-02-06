package com.flipkart.exception;

public class AdminAlreadyExistException extends Exception{
    private String adminId;

    public AdminAlreadyExistException(String adminId) {
        super();
        this.adminId = adminId;
    }

    @Override
    public String getMessage() {
        return "Admin with adminId: " + adminId + " already exist.";
    }
}
