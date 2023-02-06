package com.flipkart.exception;

public class CourseNotFoundException extends Exception{
    private int courseId;


    public CourseNotFoundException(int courseId) {
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
