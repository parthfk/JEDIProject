package com.flipkart.exception;

public class StudentAlreadyExistException extends Exception{
    private String emailId;

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
        return "Student with studentId: " + emailId + " is already exist.";
    }
}
