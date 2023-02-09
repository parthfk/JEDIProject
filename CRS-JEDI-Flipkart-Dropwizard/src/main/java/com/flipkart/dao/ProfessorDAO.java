package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDAO {

    /**
     * Method to select course to teach from Database
     * @param course
     */
    void selectCourseDAO(String courseId);

    /**
     * Method to get students enrolled in a course using SQL
     * @param courseId
     * @param semesterId
     * @return List of students
     */
    List<Student> viewEnrolledStudentListDao(String courseId, String semesterId);

    /**
     * Method to view the courses selected by professor to teach
     * @param id
     * @return List of courses
     */
    List<Course> viewCourseListDao(String id);

    /**
     * Method to add grade in Registered course for a student for a course in Database
     * @param studentId
     * @param semId
     * @param courseId
     * @param grade
     */
    void addGrade(String studentId,String semId,String courseId,String grade);
}
