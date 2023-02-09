package com.flipkart.dao;

import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.AdminAlreadyExistException;
import com.flipkart.exception.ProfessorAlreadyExistException;
import com.flipkart.utils.DbConnection;

import static com.flipkart.constant.DBConnection.*;
import static com.flipkart.constant.SQLConstants.*;

public class AdminDAOImpl implements AdminDAO {

    private static int noOfUsers;
    private Connection conn;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public AdminDAOImpl(){
        conn = DbConnection.getInstance().getConnection();
    }

    public boolean addAdminDAO(Admin admin) throws AdminAlreadyExistException,SQLException {

        PreparedStatement stmt;


            stmt=conn.prepareStatement(FETCH_USER_WITH_EMAIL_ID);

            stmt.setString(1, admin.getEmail());

            ResultSet rs1 = stmt.executeQuery();

            if (rs1.next()) {
                    throw new AdminAlreadyExistException(admin.getEmail());
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

        //System.out.println("Admin Registered Successfully!!");
        return true;
    }


    public boolean addProfessorDAO(Professor professor) throws ProfessorAlreadyExistException,SQLException{
        PreparedStatement stmt;


            stmt=conn.prepareStatement(FETCH_USER_WITH_EMAIL_ID);
            stmt.setString(1, professor.getEmail());

            ResultSet rs1 = stmt.executeQuery();

            if (rs1.next()) {
                throw new ProfessorAlreadyExistException(professor.getEmail());
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
        return true;

    }


    public List<List<String>> getListUnApprovedStudents() throws SQLException{
        PreparedStatement stmt = null;
        List<List<String>> unApprovedStudents = new ArrayList<>();
          stmt = conn.prepareStatement(SELECT_UNAPPROVED_STUDENTS_QUERY);
            ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("All students already approved");
                    return unApprovedStudents;
                }

                do {
                   ArrayList<String> studDetails = new ArrayList<>();
                    String eid = rs.getString("userId");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    studDetails.add(eid);
                    studDetails.add(name);
                    studDetails.add(email);
                    unApprovedStudents.add(studDetails);
                } while (rs.next());
                return unApprovedStudents;
    }
    public boolean approveStudentDAO(String studentId) throws SQLException {
        PreparedStatement stmt = null;

                stmt = conn.prepareStatement(UPDATE_STUDENT_APPROVAL_STATUS_QUERY);
                stmt.setString(1, studentId);
                int rs1 = stmt.executeUpdate();
                stmt.close();
                if (rs1 == 0) {
                    System.out.println("Enter A valid Student ID");
                    return false;
                } else {
                    System.out.println("Student with ID: " + studentId + " Approved!!");
                    return true;
                }

    }


    public int generateGradeCardDAO(String userId_of_approved_gradeCard) throws SQLException {
        PreparedStatement stmt;

            stmt = conn.prepareStatement(FETCH_GRADES_QUERY);
            stmt.setString(1, userId_of_approved_gradeCard);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("This student has not registered for any courses yet.");
                return 1;
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
                return 2;
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
            return 3;

    }
}