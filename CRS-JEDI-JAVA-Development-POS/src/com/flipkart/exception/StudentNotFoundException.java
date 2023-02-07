package com.flipkart.exception;

public class StudentNotFoundException extends Exception{
    private String emailId;

    /**
     * constructor
     * @param emailId
     */
    public StudentNotFoundException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws exception if student enters wrong credentials while logging in
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Student with emailId: " + emailId + " is not found.";
    }
}
