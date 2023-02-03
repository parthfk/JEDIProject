package com.flipkart.dao;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.constant.RoleId;

import java.sql.*;
import static com.flipkart.constant.DBConnection.*;

public class StudentDAOImpl implements StudentDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private Student student;

    public StudentDAOImpl(Student student) {
        this.student = student;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Something went wrong on the DB side");
        }
    }

    @Override
    public void signup() {
        try {
            // Fields for new User record
            String studentId, userId, name, password, email;
            int roleId;
            studentId = student.getUserId();
            name = student.getName();
            userId = studentId;
            password = student.getPassword();
            email = student.getEmail();
            roleId = RoleId.STUDENT;

            String userEntryQuery = "INSERT into User values (?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(userEntryQuery);
            stmt.setString(1, userId);  // This would set age
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setInt(5, roleId);
            stmt.executeUpdate();
            stmt.close();

            // Fields for Student record
            String address, mobileNumber, departmentID, gradeCardId;
            Date dob;
            boolean feeDone, statusApproval, gradeCardApproved;

            address = student.getAddress();
            mobileNumber = student.getMobileNumber();
            departmentID = student.getDepartmentID();
            dob = student.getDob();
            feeDone = student.isFeeDone();
            statusApproval = student.isStatusApproval();
            gradeCardApproved = student.isGradeCardApproved();
            gradeCardId = "N/A";

            String studentEntryQuery = "INSERT into Student values (?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(studentEntryQuery);
            stmt.setString(1, studentId);  // This would set age
            stmt.setDate(2, dob);
            stmt.setString(3, address);
            stmt.setString(4, mobileNumber);
            stmt.setString(5, departmentID);
            stmt.setBoolean(6, feeDone);
            stmt.setBoolean(7, statusApproval);
            stmt.setBoolean(8, gradeCardApproved);
            stmt.setString(9, gradeCardId);

            stmt.executeUpdate();
            stmt.close();

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectPrimaryCourse() {

    }

    @Override
    public void selectSecondaryCourse() {

    }

    @Override
    public void addCourse() {

    }

    @Override
    public void dropCourse() {

    }

    @Override
    public void payFee() {

    }

    @Override
    public void displayRegisteredCourses() {

    }

    @Override
    public GradeCard displayGradeCard() {
        return null;
    }
}

