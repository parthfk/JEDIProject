package com.flipkart.exception;

public class CourseNotAddedException extends Exception{

    private String courseId;

    /**
     * constructor
     * @param courseId
     */
    public CourseNotAddedException(String courseId) {
        this.courseId = courseId;
    }

    /**
     * method that throws exception if student try to register without adding the courses
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Course with courseId " + courseId + " not added in catalog.";
    }
}
