package com.flipkart.exception;

public class ProfessorNotAddedException extends Exception{
    private String professorId;

    /**
     * constructor
     * @param professorId
     */
    public ProfessorNotAddedException(String professorId) {
        super();
        this.professorId = professorId;
    }

    /**
     * method that throws exception if admin fails to add a professor
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Professor with professorId: " + professorId + " is not added.";
    }
}
