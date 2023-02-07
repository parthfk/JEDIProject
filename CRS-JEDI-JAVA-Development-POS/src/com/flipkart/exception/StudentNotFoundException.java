package com.flipkart.exception;

public class StudentNotFoundException extends Exception{
    private String studentId;

    /**
     * constructor
     * @param studentId
     */
    public StudentNotFoundException(String studentId) {
        super();
        this.studentId = studentId;
    }

    /**
     * method that throws exception if student enters wrong credentials while logging in
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Student with studentId: " + studentId + " is not found.";
    }
}
