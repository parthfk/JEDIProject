package com.flipkart.exception;

public class ProfessorNotAddedException extends Exception{
    private String emailId;

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
        return "Professor with emailId: " + emailId + " is not added.";
    }
}
