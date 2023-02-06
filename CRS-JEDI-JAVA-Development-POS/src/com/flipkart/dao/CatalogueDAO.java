package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;

import java.util.List;

public interface CatalogueDAO {
    /**
     * @param course
     * @param semID
     */
    public void addCourseInDB(Course course, String semID);

    /**
     * @return
     */
    public List<Course> fetchCatalogue();

    /**
     * @param courseId ß
     * @throws CourseNotFoundException
     */
    public void deleteCourseInDB(String courseId) throws CourseNotFoundException;
}
