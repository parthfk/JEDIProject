package com.flipkart.exception;

public class ProfessorAlreadyExistException extends Exception{
    private String emailId;

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
        return "Professor with emailId: " + emailId + " is already exist.";
    }
}
