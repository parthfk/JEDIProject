package com.flipkart.exception;

public class CourseNotAddedException extends Exception{

    private int courseId;


    public CourseNotAddedException(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Course with courseId " + courseId + " not added in catalog.";
    }
}
