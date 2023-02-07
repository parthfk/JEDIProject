package com.flipkart.service;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.exception.PaymentFailedException;
import com.flipkart.exception.PaymentNotFoundException;

public interface StudentService {
    /**
     * Method to initiate semester registration for student
     *
     */
    void registerForSem() throws PaymentNotFoundException, PaymentFailedException;
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
     * Method to add course from database
     */
    void addCourse();
    /**
     * Method to remove course from database
     */
    void dropCourse();

    /**
     * Method to initiate fees payment after course registration is done.
     */
    void payFee() ;

    /**
     * Method to view all the registered courses.
     */
    void displayRegisteredCourses();
    /**
     * Method to retrieve Grade card
     *
     * @return GradeCard object
     */
    GradeCard displayGradeCard ();

}
