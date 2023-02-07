package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminDAO {

    /** Method to add admin using SQL commands
     * @param admin
     */
    public void addAdminDAO(Admin admin);

    /** Method to add professor using SQL commands
     * @param professor
     */
    public void addProfessorDAO(Professor professor);

    /**
     * Method to approve student using SQL commands
     */
    public void approveStudentDAO();


    /**
     * Method to generate Grade Card after accessing grades using SQL Commands
     */
    public void generateGradeCardDAO();


}
