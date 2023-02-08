package com.flipkart.exception;

public class CourseNotAvailableException extends Exception{
    private String courseId;

    /**
     * constructor
     * @param courseId
     */
    public CourseNotAvailableException(String courseId) {
        this.courseId = courseId;
    }

    /**
     * method that throws exception if student try to add a course whose seats are filled
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Course with courseId " + courseId + " is not Available.";
    }
}
