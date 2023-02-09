package com.flipkart.exception;

public class CourseNotAvailableException extends Exception{
    private String courseId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param courseId
     */
    public CourseNotAvailableException(String courseId) {
        this.courseId = courseId;
    }

    /**
     * method that throws exception if student try to add a course whose seats are filled
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Course with courseId " + courseId + " is not Available."+
                ANSI_RESET;
    }
}
