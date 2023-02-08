package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorService {
    /**
     * Method to add grade for a student by the professor
     */
    void addGrade();

    /**
     * Method to retrieve all the students enrolled for a particular course.
	 * @return List of Students
	 */
    List<Student> viewEnrolledStudentList();
    /**
     * Method to select a particular course  to teach for the  professor.
     * @param course object
     */
    void selectCourse(Course course);
    /**
     * Method to get all the courses for professor.
     * @return List of Course
     */
    List<Course> viewCourseList();
}
