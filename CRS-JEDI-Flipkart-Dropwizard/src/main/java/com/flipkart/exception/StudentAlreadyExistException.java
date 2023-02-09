package com.flipkart.exception;

public class StudentAlreadyExistException extends Exception{
    private String emailId;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param emailId
     */
    public StudentAlreadyExistException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws exception if admin tries to add a student that already exists
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Student with studentId: " + emailId + " is already exist."+
                ANSI_RESET;
    }
}
