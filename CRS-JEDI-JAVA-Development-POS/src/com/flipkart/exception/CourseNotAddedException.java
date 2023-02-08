package com.flipkart.exception;

public class CourseNotAddedException extends Exception{

    private String courseId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param courseId
     */
    public CourseNotAddedException(String courseId) {
        this.courseId = courseId;
    }

    /**
     * method that throws exception if student try to register without adding the courses
     * @return exception message
     */
    @Override
    public String getMessage() {

        return ANSI_YELLOW+
                "Course with courseId " + courseId + " not added in catalog."+
                ANSI_RESET;
    }
}
