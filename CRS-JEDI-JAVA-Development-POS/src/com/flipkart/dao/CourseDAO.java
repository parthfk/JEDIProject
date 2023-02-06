package com.flipkart.dao;

import com.flipkart.bean.Course;

public interface CourseDAO {

    public boolean doesCourseExist(String courseID);

    public void addCourseToDB(Course course);

}
