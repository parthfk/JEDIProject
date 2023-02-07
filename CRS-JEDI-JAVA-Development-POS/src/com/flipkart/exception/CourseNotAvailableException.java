package com.flipkart.exception;

public class CourseNotAvailableException extends Exception{
    private int courseId;

    /**
     * constructor
     * @param courseId
     */
    public CourseNotAvailableException(int courseId) {
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
