package com.flipkart.dao;

import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.exception.AdminAlreadyExistException;
import com.flipkart.exception.ProfessorAlreadyExistException;
import com.flipkart.utils.DbConnection;

import static com.flipkart.constant.DBConnection.*;
import static com.flipkart.constant.SQLConstants.*;

public class AdminDAOImpl implements AdminDAO {

    private static int noOfUsers;
    Scanner scanner;
    private Connection conn;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public AdminDAOImpl(){
        conn = DbConnection.getInstance().getConnection();
    }

    public boolean addAdminDAO(Admin admin) {

        PreparedStatement stmt;

        try {

            stmt=conn.prepareStatement(FETCH_USER_WITH_EMAIL_ID);

            stmt.setString(1, admin.getEmail());

            ResultSet rs1 = stmt.executeQuery();

            if (rs1.next()) {

                try{
                    throw new AdminAlreadyExistException(admin.getEmail());
                }
                catch (AdminAlreadyExistException e) {
                    System.out.println(e.getMessage());
                }
                finally {
                    return false;
                }
            }


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

            stmt.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
        }
        //System.out.println("Admin Registered Successfully!!");
        return true;
    }


    public boolean addProfessorDAO(Professor professor) {
        PreparedStatement stmt;

        try {

            stmt=conn.prepareStatement(FETCH_USER_WITH_EMAIL_ID);
            stmt.setString(1, professor.getEmail());

            ResultSet rs1 = stmt.executeQuery();

            if (rs1.next()) {

                try{
                    throw new ProfessorAlreadyExistException(professor.getEmail());
                }
                catch (ProfessorAlreadyExistException e) {
                    System.out.println(e.getMessage());
                }
                finally {
                    return false;
                }
            }

            stmt = conn.prepareStatement(COUNT_USERS_WITH_SPECIFIC_ROLE_QUERY);
            stmt.setInt(1, 2);

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

            stmt.setString(1, "p"+Integer.toString(noOfUsers));  // This would set adminId
            stmt.setDate(2, professor.getDob());   // this would set "DOB"
            stmt.setString(3, professor.getAddress());
            stmt.setString(4, professor.getMobileNumber());
            stmt.setString(5, professor.getDepartmentID());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
        }
        System.out.println("Professor Registered Successfully!!");
        return true;

    }

    public void approveStudentDAO(String studentId) {
        PreparedStatement stmt = null;

        try {


                stmt = conn.prepareStatement(SELECT_UNAPPROVED_STUDENTS_QUERY);
                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("All students already approved");
                    return;
                }
                System.out.println("List of Un-Approved Students");

                StringBuffer buffer = new StringBuffer();
                Formatter fmt = new Formatter();

                //fmt.format("\n%20s %20s %20s\n", ANSI_RED+"Student ID"+ANSI_RESET, ANSI_RED+"Name"+ANSI_RESET, ANSI_RED+"E-Mail"+ANSI_RESET);

                fmt.format("\n%-17s %-17s %-17s\n", ANSI_CYAN +"Student ID"+ ANSI_RESET,ANSI_CYAN+"Name"+ANSI_RESET,ANSI_CYAN+"E-Mail"+ANSI_RESET);

                do {

                    String eid = rs.getString("userId");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    fmt.format("%-17s %-17s %-17s\n", ANSI_RESET+eid+ANSI_RESET,ANSI_RESET+name+ANSI_RESET,ANSI_RESET+email+ANSI_RESET);
                } while (rs.next());

                System.out.println(fmt);
                buffer.setLength(0);


//                System.out.println("Enter student ID to be Approved or Press # to exit");
//                Scanner sc = new Scanner(System.in);
//                String studentId = sc.next();
//                if (studentId.equals("#")) {
//                    break;
//                }

                stmt = conn.prepareStatement(UPDATE_STUDENT_APPROVAL_STATUS_QUERY);
                stmt.setString(1, studentId);
                int rs1 = stmt.executeUpdate();
                if (rs1 == 0) {
                    System.out.println("Enter A valid Student ID");
                } else {
                    System.out.println("Student with ID: " + studentId + " Approved!!");
                }
                stmt.close();
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

        }//end try
        System.out.println("Operation Completed Successfully");
    }


    public void generateGradeCardDAO(String userId_of_approved_gradeCard) {
        PreparedStatement stmt;


        try {

            stmt = conn.prepareStatement(FETCH_STUDENT_FOR_GRADECARD_GENERATION_QUERY);

            ResultSet rs = stmt.executeQuery();

            if (rs.next() == false) {
                System.out.println("No Grade card ready to be generated");
                return;
            }

            System.out.println("Student Name \t UserID \t E-Mail \t \t Department");

            do {
                System.out.println(rs.getString(1) + "\t \t" + rs.getString(2) + "\t \t \t" + rs.getString(3) + "\t" + rs.getString(4));
            } while (rs.next());

            rs.close();

//            System.out.println("Enter UserID of student to approve Grade Gard or Press # to exit");
//            scanner = new Scanner(System.in);
//            String userId_of_approved_gradeCard = scanner.next();
//            if (userId_of_approved_gradeCard.equals("#")) {
//                return;
//            }

            stmt = conn.prepareStatement(FETCH_GRADES_QUERY);
            stmt.setString(1, userId_of_approved_gradeCard);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("This student has not registered for any courses yet.");
                return;
            }

            float gradeTotal = 0;
            int num = 0;

            String semID = "1";
            boolean gradeNotAssigned = false;

            do {
                String tempGrade = rs.getString(1);
                semID = rs.getString(2);
                if (tempGrade == null || tempGrade.matches("N/A")) {
                    gradeNotAssigned = true;
                    break;
                }
                if (tempGrade.matches("A")) {
                    gradeTotal += 10;
                } else if (tempGrade.matches("A-")) {
                    gradeTotal += 9;
                } else if (tempGrade.matches("B")) {
                    gradeTotal += 8;
                } else if (tempGrade.matches("B-")) {
                    gradeTotal += 7;
                } else if (tempGrade.matches("C")) {
                    gradeTotal += 6;
                } else if (tempGrade.matches("C-")) {
                    gradeTotal += 5;
                } else if (tempGrade.matches("D")) {
                    gradeTotal += 4;
                }

                num += 10;
            }
            while (rs.next());

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

            stmt.setInt(1, records);
            stmt.setString(2, userId_of_approved_gradeCard);
            stmt.setFloat(3, sgpa);
            stmt.setString(4, semID);

            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement(UPDATE_STUDENT_GRADE_STATUS_QUERY);
            stmt.setInt(1, records);
            stmt.setString(2, userId_of_approved_gradeCard);


            stmt.executeUpdate();

            stmt.close();

            System.out.println("GradeCard generated!");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}