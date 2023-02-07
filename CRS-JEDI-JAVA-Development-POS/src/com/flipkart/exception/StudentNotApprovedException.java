package com.flipkart.exception;

public class StudentNotApprovedException extends Exception{
    private String studentId;

    /**
     * constructor
     * @param studentId
     */
    public StudentNotApprovedException(String studentId) {
        super();
        this.studentId = studentId;
    }

    /**
     * method that throws exception if student who is not approved tries to log in
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Student with studentId: " + studentId + " is not approved.";
    }
}
