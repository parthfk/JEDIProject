package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.util.List;

public interface CatalogueDAO {
    public void addCourseInDB(Course course, String semID);
    public List<Course> fetchCatalogue();
    public void deleteCourseInDB(String courseId);
}
