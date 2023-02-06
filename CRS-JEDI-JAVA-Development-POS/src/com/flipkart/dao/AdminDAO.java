package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminDAO {

    public void addAdminDAO(Admin admin);
    public void addProfessorDAO(Professor professor);
    public void approveStudentDAO();

}
