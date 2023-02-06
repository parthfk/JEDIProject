package com.flipkart.exception;

public class StudentNotFoundException extends Exception{
    private String studentId;

    public StudentNotFoundException(String studentId) {
        super();
        this.studentId = studentId;
    }

    @Override
    public String getMessage() {
        return "Student with studentId: " + studentId + " is not found.";
    }
}
