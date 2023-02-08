package com.flipkart.exception;

public class GradeNotAddedException extends Exception{
    private String studentId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

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
        return ANSI_YELLOW+
                "Grade not allotted yet to all subject for: " + studentId+
                ANSI_RESET;
    }
}
