package com.flipkart.service;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;

import java.sql.SQLException;

public interface StudentService {
    /**
     * Method to initiate semester registration for student
     *
     */
    //void registerForSem();

    /**
     * Method to register a student, although student can't login until it's approved by admin
     */
    void signup(Student newStudent);

    /**
     * Method to select 4 primary courses for course registration
     */
    void selectPrimaryCourse(String studentId, String pc1, String pc2, String pc3, String pc4);

    /**
     * Method to select 2 secondary courses for course registration
     */
    void selectSecondaryCourse(String studentId, String sc1, String sc2);
    /**
     * Method to add course during add-drop window
     */
    void addCourse() throws SQLException;
    /**
     * Method to remove course during add-drop window
     */
    void dropCourse() throws CourseNotFoundException;

    /**
     * Method to view all the registered courses.
     */
    void displayRegisteredCourses();
    /**
     * Method to retrieve Grade card
     *
     */
    String displayGradeCard (String studentId);

}
