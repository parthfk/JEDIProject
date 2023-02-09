package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface CatalogueDAO {

    /** Method to add course in Catalogue using SQL commands
     * @param course
     * @param semID
     */
    public boolean addCourseInDB(Course course, String semID) throws SQLException;

    /** Method to fetch Courses in catalogue on database level
     * @return list of courses
     */
    public List<Course> fetchCatalogue(boolean allCourses) throws SQLException;

    /** Method to delete course from catalogue in DB using SQl commands
     * @param courseId ß
     * @throws CourseNotFoundException
     */
    public boolean deleteCourseInDB(String courseId) throws CourseNotFoundException,Exception;
}
