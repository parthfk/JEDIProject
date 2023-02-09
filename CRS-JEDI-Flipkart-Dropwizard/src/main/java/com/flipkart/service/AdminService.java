package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    /**
     * Method to add a course by the admin
     * @return boolean
     */
    boolean addCourse(String semesterId,String courseId,String courseName,int seatsAvailable)throws SQLException;

    /**
     * Method to remove a course by the admin
     * @return boolean
     */
    boolean removeCourse(String id_to_be_deleted) throws CourseNotFoundException,SQLException ;

    /**
     * Method to approve a student by admin.
     */
    boolean approveStudent(String studentId) throws SQLException;

    /**
     * Method to add a professor to the system by the admin
     *
     */
    boolean addProfessor(Professor newProf) throws ProfessorAlreadyExistException,SQLException;

    /**
     * Method to add another admin
     * @return boolean
     */
    boolean addAdmin(Admin newAdmin)throws AdminAlreadyExistException, AdminNotAddedException,SQLException;

    /**
     * Method to generate grade card of the student
     */
    int generateGradeCard(String userId_of_approved_gradeCard) throws SQLException;

    /**
     * Method to get list of unapproved students
     */
    List<List<String>> getUnApprovedStudents() throws SQLException;

}
