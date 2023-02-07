package com.flipkart.exception;

public class StudentAlreadyExistException extends Exception{
    private String studentId;

    /**
     * constructor
     * @param studentId
     */
    public StudentAlreadyExistException(String studentId) {
        super();
        this.studentId = studentId;
    }

    /**
     * method that throws exception if admin tries to add a student that already exists
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Student with studentId: " + studentId + " is already exist.";
    }
}
