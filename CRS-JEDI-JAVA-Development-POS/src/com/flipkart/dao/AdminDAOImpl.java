package com.flipkart.dao;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;

import static com.flipkart.constant.DBConnection.*;
import static com.flipkart.constant.SQLConstants.*;

public class AdminDAOImpl implements AdminDAO {

    private static int noOfUsers;
    Scanner scanner;

    public void addAdminDAO(Admin admin) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {


            Class.forName(JDBC_DRIVER);


            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement(COUNT_USERS_QUERY);

            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                noOfUsers = rs.getInt(1);

            noOfUsers++;

            stmt = conn.prepareStatement(INSERT_USER_QUERY);

            stmt.setString(1, Integer.toString(noOfUsers));
            stmt.setString(2, admin.getName());
            stmt.setString(3, admin.getPassword());
            stmt.setString(4, admin.getEmail());
            stmt.setInt(5, 1);

            stmt.executeUpdate();

            stmt = conn.prepareStatement(INSERT_ADMIN_QUERY);

            stmt.setString(1, Integer.toString(noOfUsers));
            stmt.setDate(2, admin.getDob());
            stmt.setString(3, admin.getAddress());
            stmt.setString(4, admin.getMobileNumber());

            stmt.executeUpdate();

            conn.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Admin Registered Successfully!!");

    }


    public void addProfessorDAO(Professor professor) {


        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement(COUNT_USERS_WITH_SPECIFIC_ROLE_QUERY);
            stmt.setInt(1,2);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                noOfUsers = rs.getInt(1);

            noOfUsers++;

            stmt = conn.prepareStatement(INSERT_USER_QUERY);
            stmt.setString(1, "p" + Integer.toString(noOfUsers));
            stmt.setString(2, professor.getName());
            stmt.setString(3, professor.getPassword());
            stmt.setString(4, professor.getEmail());
            stmt.setInt(5, 2);

            stmt.executeUpdate();

            stmt = conn.prepareStatement(INSERT_PROFESSOR_QUERY);

            stmt.setString(1, Integer.toString(noOfUsers));  // This would set adminId
            stmt.setDate(2, professor.getDob());   // this would set "DOB"
            stmt.setString(3, professor.getAddress());
            stmt.setString(4, professor.getMobileNumber());
            stmt.setString(5, professor.getDepartmentID());

            stmt.executeUpdate();

            conn.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Professor Registered Successfully!!");

    }

    public void approveStudentDAO() {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            while (true) {

                stmt = conn.prepareStatement(SELECT_UNAPPROVED_STUDENTS_QUERY);
                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("All students already approved");
                    return;
                }
                System.out.println("List of Un-Approved Students");
                do {

                    String eid = rs.getString("userId");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    System.out.print("Student ID: " + eid);
                    System.out.print(" Name: " + name);
                    System.out.println(" E-Mail: " + email);
                } while (rs.next());


                System.out.println("Enter student ID to be Approved or Press # to exit");
                Scanner sc = new Scanner(System.in);
                String studentid = sc.next();
                if (studentid.equals("#")) {
                    break;
                }

                stmt = conn.prepareStatement(UPDATE_STUDENT_APPROVAL_STATUS_QUERY);
                stmt.setString(1,studentid);
                int rs1 = stmt.executeUpdate();
                if (rs1 == 0) {
                    System.out.println("Enter A valid Student ID");
                } else {
                    System.out.println("Student with ID: " + studentid + " Approved!!");
                }

            }
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Operation Completed Successfully");
    }


    public void generateGradeCardDAO() {

        Connection conn;
        PreparedStatement stmt;


        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            stmt = conn.prepareStatement(FETCH_STUDENT_FOR_GRADECARD_GENERATION_QUERY);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Name of student : " + rs.getString(1) + " UserID : " +
                        rs.getString(2) + " Email " + rs.getString(3) +
                        " Department Registered in " + rs.getString(4));
            }

            rs.close();

            System.out.println("Enter UserID of student to approve Grade Gard or Press # to exit");
            scanner = new Scanner(System.in);
            String userId_of_approved_gradeCard = scanner.next();
            if (userId_of_approved_gradeCard.equals("#")) {
                return;
            }

            stmt = conn.prepareStatement(FETCH_GRADES_QUERY);
            stmt.setString(1,userId_of_approved_gradeCard);
            rs = stmt.executeQuery();

            if(!rs.next())
            {
                System.out.println("This student has not registered any courses yet.");
                return;
            }

            float gradeTotal = 0;
            int num = 0;

            String semID = "1";
            boolean gradeNotAssigned = false;

            while (rs.next()) {
                String tempgrade = rs.getString(1);
                semID = rs.getString(2);
                if (tempgrade == null || tempgrade.matches("N/A")) {
                    gradeNotAssigned = true;
                    break;
                }
                if (tempgrade.matches("A+")) {
                    gradeTotal += 10;
                } else if (tempgrade.matches("A-")) {
                    gradeTotal += 9;
                } else if (tempgrade.matches("B+")) {
                    gradeTotal += 8;
                } else if (tempgrade.matches("B-")) {
                    gradeTotal += 7;
                } else if (tempgrade.matches("C+")) {
                    gradeTotal += 6;
                } else if (tempgrade.matches("C-")) {
                    gradeTotal += 5;
                } else if (tempgrade.matches("D+")) {
                    gradeTotal += 4;
                }

                num += 10;
            }

            rs.close();

            if (gradeNotAssigned) {
                System.out.println("Cannot generate Grade Card, few courses are yet to be assigned grades for this student ");
                return;
            }

            stmt = conn.prepareStatement(COUNT_GRADECARDS_QUERY);
            rs = stmt.executeQuery();
            int records = 2312;
            if (rs.next())
                records = rs.getInt(1);
            else
                System.out.println("DB Operation Failed");

            rs.close();

            float sgpa = (gradeTotal / num) * 10;

            stmt = conn.prepareStatement(INSERT_GRADECARD_QUERY);

            stmt.setInt(1,records);
            stmt.setString(2,userId_of_approved_gradeCard);
            stmt.setFloat(3,sgpa);
            stmt.setString(4,semID);

            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement(UPDATE_STUDENT_GRADE_STATUS_QUERY);
            stmt.setInt(1, records);
            stmt.setString(2,userId_of_approved_gradeCard);


            stmt.executeUpdate();

            conn.close();

            System.out.println("GradeCard generated!");


        } catch (SQLException se){
            System.out.println(se);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


}