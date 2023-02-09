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
    public boolean doesCourseExist(String courseID) throws SQLException{
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
        }


    @Override
    public boolean addCourseToDB(Course course) throws SQLException {

            stmt=conn.prepareStatement(INSERT_IN_COURSE_QUERY);
            stmt.setString(1, course.getCourseID());
            stmt.setString(2, course.getName());

            return stmt.executeUpdate() == 1;
        }

}

