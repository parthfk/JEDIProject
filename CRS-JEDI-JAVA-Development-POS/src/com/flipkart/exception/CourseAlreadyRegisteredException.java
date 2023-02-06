package com.flipkart.exception;

public class CourseAlreadyRegisteredException extends Exception{
    private int courseId;


    public CourseAlreadyRegisteredException(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Course with courseId " + courseId + " already exist in catalog.";
    }
}
