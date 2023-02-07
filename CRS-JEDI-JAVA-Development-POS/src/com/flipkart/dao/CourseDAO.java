package com.flipkart.dao;

import com.flipkart.bean.Course;

public interface CourseDAO {

    /**
     * Method to check if course exists in Database using SQL
     * @param courseID
     * @return boolean
     */
    public boolean doesCourseExist(String courseID);

    /**
     * Method to add course in legacy Course Database using SQL
     * @param course 
     */
    public void addCourseToDB(Course course);

}
