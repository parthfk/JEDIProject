package com.flipkart.exception;

public class CourseNotAvailableException extends Exception{
    private int courseId;


    public CourseNotAvailableException(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Course with courseId " + courseId + " is not Available.";
    }
}
