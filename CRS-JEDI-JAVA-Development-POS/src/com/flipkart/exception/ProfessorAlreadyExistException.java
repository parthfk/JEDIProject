package com.flipkart.exception;

public class ProfessorAlreadyExistException extends Exception{
    private String professorId;

    public ProfessorAlreadyExistException(String professorId) {
        super();
        this.professorId = professorId;
    }

    @Override
    public String getMessage() {
        return "Professor with professorId: " + professorId + " is already exist.";
    }
}
