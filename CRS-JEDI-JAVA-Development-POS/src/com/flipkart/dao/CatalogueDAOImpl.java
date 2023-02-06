package com.flipkart.dao;

import com.flipkart.bean.Course;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constant.DBConnection.*;



public class CatalogueDAOImpl implements CatalogueDAO{

    public CatalogueDAOImpl(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void addCourseInDB(Course course, String semID) {

        Connection conn = null;


        try{

            PreparedStatement stmt = null;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql = "insert into Catalogue values(?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,course.getCourseID());
            stmt.setString(2,semID);
            stmt.setString(3,null);
            stmt.setInt(4,course.getAvailableSeats());
            if(stmt.executeUpdate()==1){
                System.out.println("Catalogue Updated");
            }
            else {
                System.out.println("Catalogue db update failed");
            }

            stmt.close();

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public List<Course> fetchCatalogue() {
        List<Course> courseList = new ArrayList<Course>();

        Connection conn = null;


        try{

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = "select Catalogue.courseid, Course.name, Catalogue.professorid from Catalogue, Course where Catalogue.courseid = Course.courseid";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
//                Course tempcourse = new Course();

            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return courseList;
    }
}
