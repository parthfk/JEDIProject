package com.flipkart.exception;

public class GradeNotValidException extends Exception{
    private String grade;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param grade
     */
    public GradeNotValidException(String grade) {
        this.grade = grade;
    }

    /**
     * method that throws exception if grade is not in valid
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                grade+ "is not grade format : " +
                ANSI_RESET;
    }
}
