package com.flipkart.exception;

public class GradeNotAddedException extends Exception{
    private String studentId;

    public GradeNotAddedException(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String getMessage() {
        return "Grade not allotted yet to all subject for: " + studentId;
    }
}
