package com.flipkart.exception;

public class ProfessorNotFoundException extends Exception{
    private String professorId;

    /**
     * constructor
     * @param professorId
     */
    public ProfessorNotFoundException(String professorId) {
        super();
        this.professorId = professorId;
    }

    /**
     * method that throws exception if professor enters wrong credentials while logging in
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Professor with professorId: " + professorId + " is not found.";
    }
}
