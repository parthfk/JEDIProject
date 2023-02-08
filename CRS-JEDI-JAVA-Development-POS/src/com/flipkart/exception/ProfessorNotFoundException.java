package com.flipkart.exception;

public class ProfessorNotFoundException extends Exception{
    private String emailId;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param emailId
     */
    public ProfessorNotFoundException(String emailId) {
        super();
        this.emailId = emailId;
    }

    /**
     * method that throws exception if professor enters wrong credentials while logging in
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Professor with professorId: " + emailId + " is not found."+
                ANSI_RESET;
    }
}
