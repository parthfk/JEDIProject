package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.sql.SQLException;

public interface CourseDAO {

    /**
     * Method to check if course exists in Database using SQL
     * @param courseID
     * @return boolean
     */
    public boolean doesCourseExist(String courseID) throws SQLException;

    /**
     * Method to add course in legacy Course Database using SQL
     * @param course 
     */
    public boolean addCourseToDB(Course course) throws SQLException;

}
