package com.flipkart.exception;

public class StudentNotFoundException extends Exception{
    private String emailId;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

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
        return ANSI_YELLOW+
                "Student with emailId: " + emailId + " is not found."+
                ANSI_RESET;
    }
}
