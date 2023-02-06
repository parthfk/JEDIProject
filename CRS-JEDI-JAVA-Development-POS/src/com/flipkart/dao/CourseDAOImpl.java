package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.sql.*;

import static com.flipkart.constant.DBConnection.*;

public class CourseDAOImpl implements CourseDAO {

    Connection conn = null;
    PreparedStatement stmt = null;

    public CourseDAOImpl()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public boolean doesCourseExist(String courseID) {


        try {

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql = "SELECT COUNT(*) FROM Course WHERE courseId = " + courseID;


            ResultSet rs = stmt.executeQuery(sql);

            conn.close();

            if(rs.next()) {
                if(rs.getInt(1)==0) {
                    return false;
                }
                else {
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

            return false;
    }

    @Override
    public void addCourseToDB(Course course) {

        try{

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql = "INSERT INTO Course VALUES ("+course.getCourseID()+","+course.getName()+"," +")";
            stmt=conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            conn.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

