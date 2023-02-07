package com.flipkart.dao;
import com.flipkart.bean.Course;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.flipkart.constant.DBConnection.*;

public class ProfessorDAOImpl implements ProfessorDAO{
    private Professor prof;
    private Connection conn;

    public ProfessorDAOImpl(Professor prof) {
        this.prof = prof;
        conn = null;
    }

    @Override
    public void selectCourseDAO(Course course) {
        String profId = prof.getUserId();
        String courseId = course.getCourseID();
        String updateCourseQuery = "UPDATE Catalogue SET professorId='"+ profId + "' WHERE courseId='"+ courseId+"'";

        try{
            Statement st = establishConnection();

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
    public List<Student> viewEnrolledStudentListDao(String courseId, String semesterId) {
        String getStudentIdListQuery = "SELECT studentId FROM RegisteredCourse WHERE courseId='" + courseId + "' AND semesterId='"+semesterId +"'";
        try{
            Statement st = establishConnection();
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
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> viewCourseListDao(String id) {
        String getCourseListQuery = "SELECT courseId,availableSeats FROM Catalogue WHERE professorId='"+id +"'";
        try{
            Statement st = establishConnection();
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
                String getCourseDetailsQuery = "SELECT name FROM Course WHERE courseId='"+courseID +"'";
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
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addGrade(String studentId, String semId, String courseId, String grade) {
        String updateRegisteredCourse = "UPDATE RegisteredCourse SET grade='" + grade + "' WHERE studentId='"+studentId + "' AND courseId='"
                + courseId + "' AND semesterId='"+semId +"'";
        try{
            Statement st = establishConnection();

            int m = st.executeUpdate(updateRegisteredCourse);
            if (m == 1){
                System.out.println("Updated successfully : " + updateRegisteredCourse);
            }else System.out.println("Update failed");

            st.close();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    private Statement establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName(
                "com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to database.....");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement st = conn.createStatement();
        return st;
    }
}