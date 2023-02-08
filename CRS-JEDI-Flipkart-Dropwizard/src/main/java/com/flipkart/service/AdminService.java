package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;

public interface AdminService {
    /**
     * Method to add a course by the admin
     * @return boolean
     */
    boolean addCourse();

    /**
     * Method to remove a course by the admin
     * @return boolean
     */
    boolean removeCourse() ;

    /**
     * Method to approve a student by admin.
     */
    void approveStudent(String studentId);

    /**
     * Method to add a professor to the system by the admin
     *
     */
    void addProfessor();

    /**
     * Method to add another admin
     * @return boolean
     */
    boolean addAdmin();

    /**
     * Method to generate grade card of the student
     */
    void generateGradeCard(String userId_of_approved_gradeCard);

}
