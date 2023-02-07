package com.flipkart.exception;

public class AdminAlreadyExistException extends Exception{
    private String adminId;

    /**
     * constructor
     * @param adminId
     */
    public AdminAlreadyExistException(String adminId) {
        super();
        this.adminId = adminId;
    }

    /**
     * method that throws exception if we try to add an admin that already exists
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Admin with adminId: " + adminId + " already exist.";
    }
}
