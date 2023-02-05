package com.flipkart.utils;

import com.flipkart.bean.*;
import com.flipkart.constant.RoleIdMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constant.DBConnection.*;

public class Utils {
    private static Connection conn;
    private static PreparedStatement stmt = null;
    public static Course getCourseFromCourseId (String courseId) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String getCoursesQuery = "SELECT semesterId, professorId, availableSeats from Catalogue JOIN Course WHERE courseId=" + courseId;
            PreparedStatement stmt = conn.prepareStatement(getCoursesQuery);
            ResultSet rs = stmt.executeQuery(getCoursesQuery);
            if (rs.next()) {
                String courseName = rs.getString("name");
                String semesterId = rs.getString("semesterId");
                String professorId = rs.getString("professorId");
                int availableSeats = rs.getInt("availableSeats");

                return new Course(courseId, courseName, professorId, availableSeats);
            }
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong on DB side!");
        }
        return null;
    }

    public static List<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String getUsersQuery = "SELECT * from User join Student on userId=studentId";
            PreparedStatement stmt = conn.prepareStatement(getUsersQuery);
            ResultSet rs = stmt.executeQuery(getUsersQuery);
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String departmentId = rs.getString("departmentId");
                String address = rs.getString("address");
                String mobileNumber = rs.getString("mobileNumber");
                Date dob = rs.getDate("dob");

                Student s = new Student(name, email, password, departmentId, address, mobileNumber, dob);
                s.setStatusApproval(rs.getBoolean("statusApproval"));
                s.setFeeDone(rs.getBoolean("feeDone"));
                s.setGradeCardApproved(rs.getBoolean("gradeCardApproved"));
                s.setUserId(rs.getString("userId"));

                students.add(s);
            }
            stmt.close();
            conn.close();

            return students;
        } catch (SQLException e) {
            System.out.println("Something went wrong on DB side!");
        }
        return null;
    }

    public static List<Professor> getProfessorList() {
        ArrayList<Professor> professors = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String getUsersQuery = "SELECT * from User join Professor on userId=profId";
            PreparedStatement stmt = conn.prepareStatement(getUsersQuery);
            ResultSet rs = stmt.executeQuery(getUsersQuery);
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("password");
                String password = rs.getString("email");
                String departmentId = rs.getString("departmentId");
                String address = rs.getString("address");
                String mobileNumber = rs.getString("mobileNumber");
                Date dob = rs.getDate("dob");

                // todo add new Professor with data received. PLEASE CREATE CONSTRUCTOR
                // See the DB and add required fields in professor class, create constructor
            }
            stmt.close();
            conn.close();

            return professors;
        } catch (SQLException e) {
            System.out.println("Something went wrong on DB side!");
        }
        return null;
    }

    public static List<Admin> getAdminList() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String getUsersQuery = "SELECT * from User join Admin on userId=adminId";
            PreparedStatement stmt = conn.prepareStatement(getUsersQuery);
            ResultSet rs = stmt.executeQuery(getUsersQuery);
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("password");
                String password = rs.getString("email");
                String departmentId = rs.getString("departmentId");
                String address = rs.getString("address");
                String mobileNumber = rs.getString("mobileNumber");
                Date dob = rs.getDate("dob");

                // todo add new Admin with data received. PLEASE CREATE CONSTRUCTOR
                // See the DB and add required fields in admin class, create constructor
            }
            stmt.close();
            conn.close();

            return admins;
        } catch (SQLException e) {
            System.out.println("Something went wrong on DB side!");
        }
        return null;
    }

    /*
        This function might not be needed. Use the specific ones instead
     */
    public static List<User> getUserList(String type) {
        ArrayList<User> users = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String queryRoleId = "all";
            switch (type) {
                case "student":
                    queryRoleId = "3";
                    break;
                case "professor":
                    queryRoleId = "2";
                    break;
                case "admin":
                    queryRoleId = "1";
                    break;
                default:
                    queryRoleId = "all";
                    break;
            }

            String getUsersQuery = "SELECT * from User where roleId=" + queryRoleId;
            PreparedStatement stmt = conn.prepareStatement(getUsersQuery);
            ResultSet rs = stmt.executeQuery(getUsersQuery);
            if (rs.next()) {
                String userId = rs.getString("userId");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int roleId = rs.getInt("roleId");

                users.add(new User(name, password, email, RoleIdMapping.fromId(roleId)));
            }
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong on DB side!");
        }
        return null;
    }
}
