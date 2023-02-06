package com.flipkart.exception;

public class ProfessorNotFoundException extends Exception{
    private String professorId;

    public ProfessorNotFoundException(String professorId) {
        super();
        this.professorId = professorId;
    }

    @Override
    public String getMessage() {
        return "Professor with professorId: " + professorId + " is not found.";
    }
}
