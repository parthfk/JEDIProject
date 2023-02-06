package com.flipkart.dao;

import com.flipkart.bean.Course;

public interface CourseDAO {

    /**
     * @param courseID
     * @return
     */
    public boolean doesCourseExist(String courseID);

    /**
     * @param course 
     */
    public void addCourseToDB(Course course);

}
