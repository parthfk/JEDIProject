package com.flipkart.exception;

public class CourseNotFoundException extends Exception{
    private String courseId;

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
        return "Course with courseId " + courseId + " is not found.";
    }
}
