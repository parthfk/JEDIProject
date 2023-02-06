package com.flipkart.exception;

public class ProfessorNotAddedException extends Exception{
    private String professorId;

    public ProfessorNotAddedException(String professorId) {
        super();
        this.professorId = professorId;
    }

    @Override
    public String getMessage() {
        return "Professor with professorId: " + professorId + " is not added.";
    }
}
