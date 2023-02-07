package com.flipkart.exception;

public class ProfessorNotFoundException extends Exception{
    private String emailId;

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
        return "Professor with professorId: " + emailId + " is not found.";
    }
}
