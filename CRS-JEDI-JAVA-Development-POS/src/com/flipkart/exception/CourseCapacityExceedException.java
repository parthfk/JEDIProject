package com.flipkart.exception;

public class CourseCapacityExceedException extends Exception{
    private int courseId;


    public CourseCapacityExceedException(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Capacity of course with courseId " + courseId + " is exceeded.";
    }
}
