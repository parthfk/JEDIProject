package com.flipkart.service;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;

public interface StudentService {
    /**
     * Method to initiate semester registration for student
     *
     */
    void registerForSem();
    /**
     * Method to register a student, although student can't login until it's approved by admin
     */
    void signup();

    /**
     * Method to select 4 primary courses for course registration
     */
    void selectPrimaryCourse();

    /**
     * Method to select 2 secondary courses for course registration
     */
    void selectSecondaryCourse();
    /**
     * Method to add course during add-drop window
     */
    void addCourse();
    /**
     * Method to remove course during add-drop window
     */
    void dropCourse();

    /**
     * Method to initiate fees payment after course registration is done.
     */
    void payFee();

    /**
     * Method to view all the registered courses.
     */
    void displayRegisteredCourses();
    /**
     * Method to retrieve Grade card
     *
     */
    void displayGradeCard ();

}
