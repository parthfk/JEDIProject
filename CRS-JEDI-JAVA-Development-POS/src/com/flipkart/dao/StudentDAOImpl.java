package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.constant.RoleId;

import java.sql.*;
import java.util.ArrayList;

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

            String userEntryQuery = "INSERT into User values (?,?,?,?,?)";
            stmt = conn.prepareStatement(userEntryQuery);
            stmt.setString(1, userId);  // This would set age
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setInt(5, roleId);
            stmt.executeUpdate();
            stmt.close();

            // Fields for Student record
            String address, mobileNumber, departmentID;
            Date dob;
            boolean feeDone, statusApproval, gradeCardApproved;

            address = student.getAddress();
            mobileNumber = student.getMobileNumber();
            departmentID = student.getDepartmentID();
            dob = student.getDob();
            feeDone = student.isFeeDone();
            statusApproval = student.isStatusApproval();
            gradeCardApproved = student.isGradeCardApproved();

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
            stmt.setInt(9, -1);

            stmt.executeUpdate();
            stmt.close();

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectPrimaryCourse(ArrayList<Course> primaryCourses) {
        try {
            // Fields for new SemRegistration record
            String userEntryQuery = "INSERT into SemRegistration values (?,?,?,?,?,?,?,?)";

            String studentId = this.student.getUserId();
            boolean regDone = false;
            String pc1, pc2, pc3, pc4;
            pc1 = primaryCourses.get(0).getCourseID();
            pc2 = primaryCourses.get(1).getCourseID();
            pc3 = primaryCourses.get(2).getCourseID();
            pc4 = primaryCourses.get(3).getCourseID();

            stmt = conn.prepareStatement(userEntryQuery);
            stmt.setString(1, studentId);  // This would set age
            stmt.setBoolean(2, regDone);
            stmt.setString(3, pc1);
            stmt.setString(4, pc2);
            stmt.setString(5, pc3);
            stmt.setString(6, pc4);
            stmt.setString(7, "");
            stmt.setString(8, "");

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<String> viewPrimaryCourses() {
        ArrayList<String> primaryCourses = new ArrayList<>();
        try {
            String viewPrimaryCoursesQuery = "SELECT pc1, pc2, pc3, pc4 from SemRegistration " +
                    "WHERE studentId=" + student.getUserId();

            ResultSet rs = stmt.executeQuery(viewPrimaryCoursesQuery);
            while (rs.next()) {
                String pc1, pc2, pc3, pc4;
                pc1 = rs.getString("pc1");
                pc2 = rs.getString("pc2");
                pc3 = rs.getString("pc3");
                pc4 = rs.getString("pc4");
                primaryCourses.add(pc1);
                primaryCourses.add(pc2);
                primaryCourses.add(pc3);
                primaryCourses.add(pc4);
            }
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong on DB side!");
        } finally{
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored){}
            try{
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return primaryCourses;
    }

    @Override
    public void selectSecondaryCourse() {

    }

    @Override
    public void viewSecondaryCourses() {

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

