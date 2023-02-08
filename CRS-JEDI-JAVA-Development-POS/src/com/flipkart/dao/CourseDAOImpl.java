package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constant.DBConnection;
import com.flipkart.utils.DbConnection;

import java.sql.*;

import static com.flipkart.constant.DBConnection.*;
import static com.flipkart.constant.SQLConstants.*;

public class CourseDAOImpl implements CourseDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public CourseDAOImpl()
    {
        conn = DbConnection.getInstance().getConnection();
    }

    @Override
    public boolean doesCourseExist(String courseID) {
        try {

            stmt = conn.prepareStatement(CHECK_IF_COURSE_EXISTS_QUERY);
            stmt.setString(1,courseID);

            ResultSet rs = stmt.executeQuery();

            if(rs.next() && rs.getInt(1)==0) {
                System.out.println(ANSI_YELLOW+
                        "course does not exist"+
                        ANSI_RESET);
                    return false;
                }
                else {
                System.out.println("course exists");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCourseToDB(Course course) {

        try{
            stmt=conn.prepareStatement(INSERT_IN_COURSE_QUERY);
            stmt.setString(1, course.getCourseID());
            stmt.setString(2, course.getName());

            if (stmt.executeUpdate() == 1) {
                System.out.println("Insertion in Course db successful !");
            }
            else {
                System.out.println(ANSI_YELLOW+
                        "Insertion in Course db failed !"+
                        ANSI_RESET);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

