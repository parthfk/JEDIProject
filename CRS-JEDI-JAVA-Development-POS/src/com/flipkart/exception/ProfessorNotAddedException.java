package com.flipkart.exception;

public class ProfessorNotAddedException extends Exception{
    private String emailId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param emailId
     */
    public ProfessorNotAddedException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws exception if admin fails to add a professor
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Professor with emailId: " + emailId + " is not added."+
                ANSI_RESET;
    }
}
