package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constant.DBConnection;

import java.sql.*;

import static com.flipkart.constant.DBConnection.*;
import static com.flipkart.constant.SQLConstants.*;

public class CourseDAOImpl implements CourseDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;

    Connection conn = null;
    PreparedStatement stmt = null;

    public CourseDAOImpl()
    {
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public boolean doesCourseExist(String courseID) {
        try {

            stmt = conn.prepareStatement(CHECK_IF_COURSE_EXISTS_QUERY);
            stmt.setString(1,courseID);

            ResultSet rs = stmt.executeQuery();

            if(rs.next() && rs.getInt(1)==0) {
                System.out.println("course does not exist");
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
                System.out.println("Insertion in Course db failed !");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

