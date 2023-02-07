package com.flipkart.exception;

public class ProfessorAlreadyExistException extends Exception{
    private String professorId;

    /**
     * constructor
     * @param professorId
     */
    public ProfessorAlreadyExistException(String professorId) {
        super();
        this.professorId = professorId;
    }

    /**
     * method that throws exception if admin tries to add a professor that already exists
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Professor with professorId: " + professorId + " is already exist.";
    }
}
