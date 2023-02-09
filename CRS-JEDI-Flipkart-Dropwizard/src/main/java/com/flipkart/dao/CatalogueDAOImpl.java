package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constant.DBConnection;
import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constant.DBConnection.*;
import static com.flipkart.constant.SQLConstants.*;


public class CatalogueDAOImpl implements CatalogueDAO {
    private Connection conn;
    private PreparedStatement stmt = null;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public CatalogueDAOImpl() {
        conn = DbConnection.getInstance().getConnection();
    }

    @Override
    public boolean addCourseInDB(Course course, String semID) throws SQLException {

            stmt = conn.prepareStatement(INSERT_CATALOGUE_QUERY);

            stmt.setString(1, course.getCourseID());
            stmt.setString(2, semID);
            stmt.setString(3, null);
            stmt.setInt(4, course.getAvailableSeats());
            boolean res = stmt.executeUpdate()==1;
            stmt.close();
            return res;
    }

    @Override
    public List<Course> fetchCatalogue(boolean allCourses) throws SQLException {
        List<Course> courseList = new ArrayList<>();
            if (allCourses)
                stmt = conn.prepareStatement(FETCH_CATALOGUE_QUERY_ALL);
            else
                stmt = conn.prepareStatement(FETCH_CATALOGUE_QUERY);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Course tempcourse = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                courseList.add(tempcourse);
            }

            rs.close();
            return courseList;

    }

    @Override
    public boolean deleteCourseInDB(String courseId) throws CourseNotFoundException,SQLException {
            stmt = conn.prepareStatement(DELETE_FROM_CATALOGUE_QUERY);
            stmt.setString(1, courseId);

            int row = stmt.executeUpdate();
            stmt.close();
            if (row == 0) {
                throw new CourseNotFoundException(courseId);
            }
            return true;

    }
}