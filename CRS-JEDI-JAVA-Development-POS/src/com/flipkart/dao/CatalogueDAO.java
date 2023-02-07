package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;

import java.util.List;

public interface CatalogueDAO {

    /** Method to add course in Catalogue using SQL commands
     * @param course
     * @param semID
     */
    public void addCourseInDB(Course course, String semID);

    /** Method to fetch Courses in catalogue on database level
     * @return
     */
    public List<Course> fetchCatalogue();

    /** Method to delete course from catalogue in DB using SQl commands
     * @param courseId ß
     * @throws CourseNotFoundException
     */
    public void deleteCourseInDB(String courseId) throws CourseNotFoundException;
}
