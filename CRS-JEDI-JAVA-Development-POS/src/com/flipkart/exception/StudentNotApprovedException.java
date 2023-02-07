package com.flipkart.exception;

public class StudentNotApprovedException extends Exception{
    private String emailId;

    /**
     * constructor
     * @param emailId
     */
    public StudentNotApprovedException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws exception if student who is not approved tries to log in
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Student with emailId: " + emailId + " is not approved.";
    }
}
