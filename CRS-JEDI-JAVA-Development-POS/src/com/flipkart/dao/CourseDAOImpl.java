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

            String sql = "SELECT COUNT(*) FROM Course WHERE courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,courseID);

            ResultSet rs = stmt.executeQuery(sql);

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

            String sql = "INSERT INTO Course VALUES (?, ?)";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1, course.getCourseID());
            stmt.setString(2,course.getName());

            if(stmt.executeUpdate()==1){
                System.out.println("Insertion in Course db successful !");
            }
            else {
                System.out.println("Insertion in Course db  failed !");
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

