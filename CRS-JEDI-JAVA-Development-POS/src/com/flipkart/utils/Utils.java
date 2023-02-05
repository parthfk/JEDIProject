package com.flipkart.utils;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
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
