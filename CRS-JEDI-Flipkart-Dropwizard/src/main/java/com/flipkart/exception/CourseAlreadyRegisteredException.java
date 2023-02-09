package com.flipkart.exception;

public class CourseAlreadyRegisteredException extends Exception{
    private String courseId;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor
     * @param courseId
     */
    public CourseAlreadyRegisteredException(String courseId) {
        this.courseId = courseId;
    }

    /**
     * method that throws exception if student try to register for a course that he has already registered in
     * @return exception message
     */
    @Override
    public String getMessage() {
        return ANSI_YELLOW+
                "Course with courseId " + courseId + " already exist in catalog."+
                ANSI_RESET;
    }
}
