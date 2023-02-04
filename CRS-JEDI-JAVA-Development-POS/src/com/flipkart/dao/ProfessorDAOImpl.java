package com.flipkart.dao;
import com.flipkart.bean.Course;

import com.flipkart.bean.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constant.DBConnection.*;

public class ProfessorDAOImpl implements ProfessorDAO{
    private Professor prof;

    public ProfessorDAOImpl(Professor prof) {
        this.prof = prof;
    }

    @Override
    public void selectCourseDAO(Course course) {
        String profId = prof.getUserId();
        String courseId = course.getCourseID();
        String updateCourseQuery = "UPDATE Catalogue SET professorId="+ profId + " WHERE courseId="+ courseId;
        // update course list of professor --todo
        Connection conn = null;
        try{
            Class.forName(
                    "com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database.....");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement st = conn.createStatement();

            int m = st.executeUpdate(updateCourseQuery);
            if (m == 1){
                System.out.println("Updated successfully : " + updateCourseQuery);
            }else System.out.println("Update failed");

            st.close();
            conn.close();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    @Override
    public void viewEnrolledStudentListDao(String courseId, String semesterId) {
        String getStudentIdListQuery = "SELECT studentId FROM RegisteredCourse WHERE courseId=" + courseId + " AND semesterId="+semesterId;
        Connection conn = null;
        try{
            Class.forName(
                    "com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database.....");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(getStudentIdListQuery);
            List<String> studentIds = new ArrayList<>();
            while (rs.next()){
                String id = rs.getString("studentId");
                studentIds.add(id);
            }

            for(String id : studentIds){
                String getStudentDetailsList = "SELECT name FROM User WHERE userId=" + id;
                ResultSet rs2 = st.executeQuery(getStudentDetailsList);
                while(rs2.next()){
                    String name = rs2.getString("name");
                    System.out.println("Name - " + name + " , ID - " + id);
                }
            }
            st.close();
            conn.close();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}
