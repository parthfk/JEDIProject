package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface ProfessorService {
    /**
     * Method to add grade for a student by the professor
     *
     * @return
     */
    boolean addGrade(String courseId, String semId, String studentId, String grade);

    /**
     * Method to retrieve all the students enrolled for a particular course.
	 * @return List of Students
	 */
    List<Student> viewEnrolledStudentList(String courseId,String semId);
    /**
     * Method to select a particular course  to teach for the  professor.
     * @param course object
     */
    void selectCourse(String courseId) throws SQLException, CourseNotFoundException;
    /**
     * Method to get all the courses for professor.
     * @return List of Course
     */
    List<Course> viewCourseList();
}
