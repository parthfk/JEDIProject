package com.flipkart.dao;
import com.flipkart.bean.Course;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLConstants;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO{
    private String profId;
    private Connection conn;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public ProfessorDAOImpl(String profId) {
        this.profId = profId;
        conn = DbConnection.getInstance().getConnection();
    }

    @Override
    public void selectCourseDAO(String courseId) {
        try{
            PreparedStatement st = conn.prepareStatement(SQLConstants.UPDATE_COURSE_QUERY);
            st.setString(1,profId);
            st.setString(2,courseId);

            st.execute();

            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> viewEnrolledStudentListDao(String courseId, String semesterId) {
        String getStudentIdListQuery = "SELECT studentId FROM RegisteredCourse WHERE courseId='" + courseId + "' AND semesterId='" + semesterId + "'";
        try{
            PreparedStatement st = conn.prepareStatement(getStudentIdListQuery);
            ResultSet rs = st.executeQuery(getStudentIdListQuery);
            List<String> studentIds = new ArrayList<>();
            while (rs.next()){
                String id = rs.getString("studentId");
                studentIds.add(id);
            }

            List<Student> studentList = new ArrayList<>();
            for(String id : studentIds){
                String getStudentDetailsList = "SELECT * FROM User JOIN Student ON userId=studentId AND studentId='" + id +"'";
                ResultSet rs2 = st.executeQuery(getStudentDetailsList);
                while(rs2.next()){
                    String name = rs2.getString("name");
                    String email = rs2.getString("email");
                    String pass = rs2.getString("password");
                    String departmentId = rs2.getString("departmentId");
                    String address = rs2.getString("address");
                    String m_no = rs2.getString("mobileNumber");
                    Date date = rs2.getDate("dob");

                    Student s = new Student(name,email,pass,departmentId,address,m_no,date);
                    s.setUserId(id);
                    studentList.add(s);
                }
            }
            st.close();
            return studentList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> viewCourseListDao(String id) {
        String getCourseListQuery = "SELECT courseId,availableSeats FROM Catalogue WHERE professorId='" + profId + "'";
        try{
            PreparedStatement st = conn.prepareStatement(getCourseListQuery);
            ResultSet rs = st.executeQuery(getCourseListQuery);
            List<String> courseIds = new ArrayList<>();
            HashMap<String,Integer> courseToAvailableSeatsMapping = new HashMap<>();
            while (rs.next()){
                String courseID = rs.getString("courseId");
                int availableSeats = rs.getInt("availableSeats");
                courseToAvailableSeatsMapping.put(courseID,availableSeats);
                courseIds.add(courseID);
            }

            List<Course> courseList = new ArrayList<>();
            for(String courseID : courseIds){
                String getCourseDetailsQuery = "SELECT name FROM Course WHERE courseId='" + courseID + "'";
                st = conn.prepareStatement(getCourseDetailsQuery);
                ResultSet rs2 = st.executeQuery(getCourseDetailsQuery);
                while(rs2.next()){
                    String name = rs2.getString("name");
                    Course c = new Course(courseID,name,id,courseToAvailableSeatsMapping.get(courseID));
                    courseList.add(c);
                }
            }
            st.close();
            return courseList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addGrade(String studentId, String semId, String courseId, String grade) {
        try{
            PreparedStatement st = conn.prepareStatement(SQLConstants.UPDATE_REGISTERED_COURSE_QUERY);
            st.setString(1,grade);
            st.setString(2,studentId);
            st.setString(3,courseId);
            st.setString(4,semId);

            st.execute();

            st.close();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}