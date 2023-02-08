package com.flipkart.dao;

import java.text.SimpleDateFormat;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.constant.DBConnection;
import com.flipkart.exception.AdminAlreadyExistException;
import com.flipkart.exception.ProfessorAlreadyExistException;
import com.flipkart.service.AdminService;

import static com.flipkart.constant.DBConnection.*;

public class AdminDAOImpl implements AdminDAO{

    private static int noOfUsers;
    Scanner scanner;
    public boolean addAdminDAO(Admin admin){
        //  public static void main(String args[]){
        Connection conn = null;
        PreparedStatement stmt = null;
       int res=0;
        try{

            // Step 3 Regiater Driver here and create connection

            Class.forName("com.mysql.jdbc.Driver");

            // Step 4 make/open  a connection

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            String ss1="SELECT * FROM User where emailId="+admin.getEmail();
            stmt=conn.prepareStatement(ss1);

            ResultSet rs1 = stmt.executeQuery(ss1);

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


            String ss="SELECT COUNT(*) FROM User";
            stmt=conn.prepareStatement(ss);

            ResultSet rs = stmt.executeQuery(ss);
            if(rs.next())
                noOfUsers=rs.getInt("COUNT(*)");

            //System.out.println(noOfUsers);
            noOfUsers++;


            String sql="insert into User values(?,?,?,?,?)";
            //String sql = "UPDATE Admin set age=? WHERE id=?";
            // String sql1="delete from employee where id=?";
            // stmt.setInt(1, 101);

//            Admin admin = new Admin();
//            admin.setName("hello");
//            admin.setPassword("password");
//            admin.setEmail("q@gmail.com");



            stmt = conn.prepareStatement(sql);

            stmt.setString(1, Integer.toString(noOfUsers));  // This would set adminId
            stmt.setString(2,admin.getName());
            stmt.setString(3, admin.getPassword());
            stmt.setString(4, admin.getEmail());
            stmt.setInt(5, 1);

             res=stmt.executeUpdate();

            System.out.println("Creating statement...");
            sql="insert into Admin values(?,?,?,?)";
            //String sql = "UPDATE Admin set age=? WHERE id=?";
            // String sql1="delete from employee where id=?";
            // stmt.setInt(1, 101);
            stmt = conn.prepareStatement(sql);
            // how to check user already exist??
            stmt.setString(1, Integer.toString(noOfUsers));  // This would set adminId
            stmt.setDate(2,admin.getDob());   // this would set "DOB"
            stmt.setString(3, admin.getAddress());
            stmt.setString(4,admin.getMobileNumber());

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            return res==1;
        }//end try


    }


    public boolean addProfessorDAO(Professor professor){


        Connection conn = null;
        PreparedStatement stmt = null;
        int res=0;
        try{

            Class.forName(JDBC_DRIVER);


            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            String ss1="SELECT * FROM User where emailId="+professor.getEmail();
            stmt=conn.prepareStatement(ss1);

            ResultSet rs1 = stmt.executeQuery(ss1);

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

            String ss="SELECT COUNT(*) FROM User WHERE roleId=2";
            stmt=conn.prepareStatement(ss);

            ResultSet rs = stmt.executeQuery(ss);
            if(rs.next())
                noOfUsers=rs.getInt("COUNT(*)");
            noOfUsers++;

            //System.out.println(noOfUsers);


            String sql="insert into User values(?,?,?,?,?)";
            //String sql = "UPDATE Admin set age=? WHERE id=?";
            // String sql1="delete from employee where id=?";
            // stmt.setInt(1, 101);

//            Professor professor = new Professor();
//            professor.setName("hello");
//            professor.setPassword("password");
//            professor.setEmail("q@gmail.com");



            stmt = conn.prepareStatement(sql);
            // how to check already Admin exist
            stmt.setString(1, "p" + Integer.toString(noOfUsers));  // This would set adminId
            stmt.setString(2,professor.getName());
            stmt.setString(3, professor.getPassword());
            stmt.setString(4, professor.getEmail());
            stmt.setInt(5, 2);

            res=stmt.executeUpdate();

            System.out.println("Creating statement...");
            sql="insert into Professor values(?,?,?,?,?)";
            //String sql = "UPDATE Admin set age=? WHERE id=?";
            // String sql1="delete from employee where id=?";
            // stmt.setInt(1, 101);
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, Integer.toString(noOfUsers));  // This would set adminId
            stmt.setDate(2,professor.getDob());   // this would set "DOB"
            stmt.setString(3,professor.getAddress());
            stmt.setString(4,professor.getMobileNumber());
            stmt.setString(5, professor.getDepartmentID());


            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            System.out.println("Professor Registered Successfully!!");
            return res==1;
        }//end try


    }

    public void approveStudentDAO(){

        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            // Step 3 Regiater Driver here and create connection

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            while(true) {
                String sql = "SELECT * FROM Student JOIN User ON Student.studentId = User.userId WHERE Student.statusApproval=0";

                stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql);

                //STEP 5: Extract data from result set
                if (rs.next()==false) {
                    System.out.println("All students already approved");
                    return;
                }
                System.out.println("List of Un-Approved Students");
                do{
                    //Retrieve by column name


                    String eid = rs.getString("userId");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    System.out.print("Student ID: " + eid);
                    System.out.print(" Name: " + name);
                    System.out.println(" E-Mail: " + email);
                }while(rs.next());


                System.out.println("Enter student ID to be Approved or Press # to exit");
                Scanner sc = new Scanner(System.in);
                String studentid = sc.next();
                if (studentid.equals("#")) {
                    break;
                }

                sql = "UPDATE Student SET statusApproval=1 WHERE studentId=" + "'" + studentid + "'";
                stmt = conn.prepareStatement(sql);
                int rs1=stmt.executeUpdate(sql);
                if(rs1==0)
                {
                    System.out.println("Enter A valid Student ID");
                } else {
                    System.out.println("Student with ID: " + studentid + " Approved!!");
                }


            }
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }


    public void generateGradeCardDAO(){

        Connection conn;
        PreparedStatement stmt;



        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // fetch student list
            // select * from student

            String sql = "select name, studentId, email, departmentId from Student JOIN User on userId = studentId where gradeCardApproved = 0 "+
                    "and statusApproval = 1";

            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
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

            sql = "select grade, semesterId from RegisteredCourse where studentId = '"+userId_of_approved_gradeCard +"'";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            float gradeTotal = 0;
            int num = 0;

            String semID="1";
            boolean gradeNotAssigned = false;

            while(rs.next())
            {
                String tempgrade = rs.getString(1);
                semID = rs.getString(2);
                if(tempgrade == null || tempgrade.matches("N/A")){
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

            if(gradeNotAssigned)
            {
                System.out.println("Cannot generate Grade Card, few courses are yet to be assigned grades for this student ");
                return;
            }

            sql = "select count(*) from GradeCard";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            int records = 2312;
            if(rs.next())
                    records = rs.getInt(1);

            rs.close();

            float sgpa = (gradeTotal/num) * 10;
            sql = "insert into GradeCard values ('"+records+"','"+userId_of_approved_gradeCard+"',"+
                    sgpa +",'"+
                    semID+"')";

            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();
            stmt.close();

            sql = "UPDATE Student set gradeCardApproved = 1, gradeCardId = ? WHERE studentId = '" + userId_of_approved_gradeCard +"'";
            stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, records);
            stmt.executeUpdate();

            System.out.println("GradeCard generated!");


        } catch (Exception e){
            e.printStackTrace();
        }


    }









}