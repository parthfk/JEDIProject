package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.AdminAlreadyExistException;
import com.flipkart.exception.ProfessorAlreadyExistException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {

    /**
     * Method to add admin using SQL commands
     * @param admin
     */
    public boolean addAdminDAO(Admin admin) throws AdminAlreadyExistException,SQLException;

    /**
     * Method to add professor using SQL commands
     * @param professor
     */
    public boolean addProfessorDAO(Professor professor) throws ProfessorAlreadyExistException,SQLException;

    /**
     * Method to approve student using SQL commands
     */
    public boolean approveStudentDAO(String studentId) throws SQLException;


    /**
     * Method to generate Grade Card after accessing grades using SQL Commands
     */
    public int generateGradeCardDAO(String userId_of_approved_gradeCard) throws SQLException;

    /**
     * Method to get list of unapproved student details
     */
    public List<List<String>> getListUnApprovedStudents() throws SQLException;


}
