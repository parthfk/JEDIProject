package com.flipkart.exception;

public class GradeNotAddedException extends Exception{
    private String studentId;
    /**
     * constructor
     * @param studentId
     */
    public GradeNotAddedException(String studentId) {
        this.studentId = studentId;
    }

    /**
     * method that throws exception if student tries to request grade card before  grades are added
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Grade not allotted yet to all subject for: " + studentId;
    }
}
