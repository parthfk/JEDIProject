package com.flipkart.exception;

public class CourseAlreadyRegisteredException extends Exception{
    private String courseId;

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
        return "Course with courseId " + courseId + " already exist in catalog.";
    }
}
