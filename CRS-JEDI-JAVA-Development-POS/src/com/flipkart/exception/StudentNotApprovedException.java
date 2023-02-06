package com.flipkart.exception;

public class StudentNotApprovedException extends Exception{
    private String studentId;

    public StudentNotApprovedException(String studentId) {
        super();
        this.studentId = studentId;
    }

    @Override
    public String getMessage() {
        return "Student with studentId: " + studentId + " is not approved.";
    }
}
