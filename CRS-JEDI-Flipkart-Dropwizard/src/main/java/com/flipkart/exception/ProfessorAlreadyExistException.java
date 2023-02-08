package com.flipkart.exception;

public class ProfessorAlreadyExistException extends Exception{
    private String emailId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param emailId
     */
    public ProfessorAlreadyExistException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws exception if admin tries to add a professor that already exists
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Professor with emailId: " + emailId + " is already exist."+
                ANSI_RESET;
    }
}
