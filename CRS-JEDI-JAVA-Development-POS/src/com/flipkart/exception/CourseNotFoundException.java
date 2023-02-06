package com.flipkart.exception;

public class CourseNotFoundException extends Exception{
    private String courseId;


    public CourseNotFoundException(String courseId) {
        this.courseId = courseId;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Course with courseId " + courseId + " is not found.";
    }
}
