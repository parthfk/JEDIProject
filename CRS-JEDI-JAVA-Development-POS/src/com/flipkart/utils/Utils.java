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

    Utils(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Course getCourseFromCourseId (String courseId) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String getCoursesQuery = "SELECT * from Catalogue JOIN Course WHERE Catalogue.courseId=" + courseId + " LIMIT 1";
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
            System.out.println(e.getMessage());
            System.out.println("Something went wrong on DB side!");
        }
        return null;
    }

    public static List<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

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
            System.out.println(e.getMessage());
            System.out.println("Something went wrong on DB side!");
        }
        return null;
    }

    public static List<Professor> getProfessorList() {
        ArrayList<Professor> professors = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String getUsersQuery = "SELECT * from User join Professor on userId=profId";
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

                Professor p = new Professor(name, email, password, departmentId, address, mobileNumber, dob);
                p.setUserId(rs.getString("userId"));
                professors.add(p);
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
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String getUsersQuery = "SELECT * from User join Admin on userId=adminId";
            PreparedStatement stmt = conn.prepareStatement(getUsersQuery);
            ResultSet rs = stmt.executeQuery(getUsersQuery);
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String mobileNumber = rs.getString("mobileNumber");
                Date dob = rs.getDate("dob");

                Admin a = new Admin(name,email,password,address,mobileNumber,dob);
                a.setUserId(rs.getString("userId"));
                admins.add(a);

            }
            stmt.close();
            conn.close();

            return admins;
        } catch (SQLException e) {
            System.out.println("Something went wrong on DB side!");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
        This function might not be needed. Use the specific ones instead
     */
    public static List<User> getUserList(String type) {
        ArrayList<User> users = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

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

    public static void updateCourseSeats(Course c) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Fields for new SemRegistration record
            String userEntryQuery =
                    "UPDATE Catalogue SET availableSeats=? WHERE courseId=?";

            String courseId = c.getCourseID();
            int availableSeatsUpdated = c.getAvailableSeats() - 1;
            stmt = conn.prepareStatement(userEntryQuery);
            stmt.setInt(1, availableSeatsUpdated);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}