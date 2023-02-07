package com.flipkart.dao;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.exception.AdminAlreadyExistException;

import static com.flipkart.constant.DBConnection.*;

public class AdminDAOImpl implements AdminDAO {
    private static int noOfUsers;
    private Scanner scanner;

    public boolean addAdminDAO(Admin admin) {
        Connection conn;
        PreparedStatement stmt;
        int res = 0;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String ss1 = "SELECT * FROM User where emailId=" + admin.getEmail();
            stmt = conn.prepareStatement(ss1);

            ResultSet rs1 = stmt.executeQuery(ss1);
            if (!rs1.next()) {
                try {
                    throw new AdminAlreadyExistException(admin.getEmail());
                } catch (AdminAlreadyExistException e) {
                    System.out.println(e.getMessage());
                } finally {
                    return false;
                }
            }

            String ss = "SELECT COUNT(*) FROM User";
            stmt = conn.prepareStatement(ss);

            ResultSet rs = stmt.executeQuery(ss);
            if (rs.next())
                noOfUsers = rs.getInt("COUNT(*)");

            noOfUsers++;


            String sql = "insert into User values(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, Integer.toString(noOfUsers));  // This would set adminId
            stmt.setString(2, admin.getName());
            stmt.setString(3, admin.getPassword());
            stmt.setString(4, admin.getEmail());
            stmt.setInt(5, 1);

            res = stmt.executeUpdate();

            sql = "insert into Admin values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            // how to check user already exist??
            stmt.setString(1, Integer.toString(noOfUsers));  // This would set adminId
            stmt.setDate(2, admin.getDob());   // this would set "DOB"
            stmt.setString(3, admin.getAddress());
            stmt.setString(4, admin.getMobileNumber());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            return res == 1;
        }
    }


    public void addProfessorDAO(Professor professor) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String ss = "SELECT COUNT(*) FROM User WHERE roleId=2";
            stmt = conn.prepareStatement(ss);

            ResultSet rs = stmt.executeQuery(ss);
            if (rs.next())
                noOfUsers = rs.getInt("COUNT(*)");
            noOfUsers++;


            String sql = "insert into User values(?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);
            // how to check already Admin exist
            stmt.setString(1, "p" + Integer.toString(noOfUsers));  // This would set adminId
            stmt.setString(2, professor.getName());
            stmt.setString(3, professor.getPassword());
            stmt.setString(4, professor.getEmail());
            stmt.setInt(5, 2);

            stmt.executeUpdate();

            System.out.println("Creating statement...");
            sql = "insert into Professor values(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, Integer.toString(noOfUsers));  // This would set adminId
            stmt.setDate(2, professor.getDob());   // this would set "DOB"
            stmt.setString(3, professor.getAddress());
            stmt.setString(4, professor.getMobileNumber());
            stmt.setString(5, professor.getDepartmentID());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Professor Registered Successfully!");
    }

    public void approveStudentDAO() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            while (true) {
                String sql = "SELECT * FROM Student JOIN User ON Student.studentId = User.userId WHERE Student.statusApproval=0";

                stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next() == false) {
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
                String studentId = sc.next();
                if (studentId.equals("#")) {
                    break;
                }

                sql = "UPDATE Student SET statusApproval=1 WHERE studentId=" + "'" + studentId + "'";
                stmt = conn.prepareStatement(sql);
                int rs1 = stmt.executeUpdate(sql);
                if (rs1 == 0) {
                    System.out.println("Enter A valid Student ID");
                } else {
                    System.out.println("Student with ID: " + studentId + " Approved!!");
                }


            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    }


    public void generateGradeCardDAO() {
        Connection conn;
        PreparedStatement stmt;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // fetch student list
            // select * from student

            String sql = "select name, studentId, email, departmentId from Student JOIN User on userId = studentId where gradeCardApproved = 0 " +
                    "and statusApproval = 1";

            stmt = conn.prepareStatement(sql);

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

            sql = "select grade, semesterId from RegisteredCourse where studentId = '" + userId_of_approved_gradeCard + "'";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            float gradeTotal = 0;
            int num = 0;

            String semID = "1";
            boolean gradeNotAssigned = false;

            while (rs.next()) {
                String tempGrade = rs.getString(1);
                semID = rs.getString(2);
                if (tempGrade == null || tempGrade.matches("N/A")) {
                    gradeNotAssigned = true;
                    break;
                }
                if (tempGrade.matches("A+")) {
                    gradeTotal += 10;
                } else if (tempGrade.matches("A-")) {
                    gradeTotal += 9;
                } else if (tempGrade.matches("B+")) {
                    gradeTotal += 8;
                } else if (tempGrade.matches("B-")) {
                    gradeTotal += 7;
                } else if (tempGrade.matches("C+")) {
                    gradeTotal += 6;
                } else if (tempGrade.matches("C-")) {
                    gradeTotal += 5;
                } else if (tempGrade.matches("D+")) {
                    gradeTotal += 4;
                }

                num += 10;
            }

            rs.close();

            if (gradeNotAssigned) {
                System.out.println("Cannot generate Grade Card, few courses are yet to be assigned grades for this student ");
                return;
            }

            sql = "select count(*) from GradeCard";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            int records = 2312;
            if (rs.next())
                records = rs.getInt(1);

            rs.close();

            float sgpa = (gradeTotal / num) * 10;
            sql = "insert into GradeCard values ('" + records + "','" + userId_of_approved_gradeCard + "'," +
                    sgpa + ",'" +
                    semID + "')";

            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();
            stmt.close();

            sql = "UPDATE Student set gradeCardApproved = 1, gradeCardId = ? WHERE studentId = '" + userId_of_approved_gradeCard + "'";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, records);
            stmt.executeUpdate();

            System.out.println("GradeCard generated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}