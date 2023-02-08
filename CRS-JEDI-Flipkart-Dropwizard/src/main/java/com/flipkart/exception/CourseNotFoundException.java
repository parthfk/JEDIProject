package com.flipkart.exception;

public class CourseNotFoundException extends Exception{
    private String courseId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param courseId
     */
    public CourseNotFoundException(String courseId) {
        this.courseId = courseId;
    }

    /**
     * method that throws exception if student tries to add a course that does not exists
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Course with courseId " + courseId + " is not found."+
                ANSI_RESET;
    }
}
