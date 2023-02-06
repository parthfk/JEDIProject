package com.flipkart.exception;

public class StudentAlreadyExistException extends Exception{
    private String studentId;

    public StudentAlreadyExistException(String studentId) {
        super();
        this.studentId = studentId;
    }

    @Override
    public String getMessage() {
        return "Student with studentId: " + studentId + " is already exist.";
    }
}
