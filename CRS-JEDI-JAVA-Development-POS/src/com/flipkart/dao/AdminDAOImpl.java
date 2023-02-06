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
import com.flipkart.service.AdminService;

import static com.flipkart.constant.DBConnection.*;

public class AdminDAOImpl implements AdminDAO{

    private  static int noOfUsers;
    Scanner scanner;
    public void addAdminDAO(Admin admin){
        //  public static void main(String args[]){
        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            // Step 3 Regiater Driver here and create connection

            Class.forName("com.mysql.jdbc.Driver");

            // Step 4 make/open  a connection

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            String ss="SELECT COUNT(*) FROM User";
            stmt=conn.prepareStatement(ss);

            ResultSet rs = stmt.executeQuery(ss);
            if(rs.next())
                noOfUsers=rs.getInt("COUNT(*)");

            //System.out.println(noOfUsers);



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

            stmt.executeUpdate();

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


			   /*
			   // Let us update age of the record with ID = 102;
			      int rows = stmt.executeUpdate();
			      System.out.println("Rows impacted : " + rows );
			      // Let us select all the records and display them.
			      sql = "SELECT id, name ,address, location FROM employeefc";
			      ResultSet rs = stmt.executeQuery(sql);
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			         //Retrieve by column name
			         int eid  = rs.getInt("id");
			         String name1 = rs.getString("name");
			         String address1 = rs.getString("address");
			         String location1 = rs.getString("location");
			         //Display values
			         System.out.print("ID: " + eid);
			         System.out.print(", Age: " + name1);
			         System.out.print(", First: " + address1);
			         System.out.println(", Last: " + location1);
			      }*/
            //STEP 6: Clean-up environment
            // rs.close();
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
        System.out.println("Admin Registered Successfuly!!");

    }


    public void addProfessorDAO(Professor professor){


        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            // Step 3 Regiater Driver here and create connection

            Class.forName("com.mysql.jdbc.Driver");

            // Step 4 make/open  a connection

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            String ss="SELECT COUNT(*) FROM User";
            stmt=conn.prepareStatement(ss);

            ResultSet rs = stmt.executeQuery(ss);
            if(rs.next())
                noOfUsers=rs.getInt("COUNT(*)");

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
            stmt.setString(1, Integer.toString(noOfUsers));  // This would set adminId
            stmt.setString(2,professor.getName());
            stmt.setString(3, professor.getPassword());
            stmt.setString(4, professor.getEmail());
            stmt.setInt(5, 2);

            stmt.executeUpdate();

            System.out.println("Creating statement...");
            sql="insert into Professor values(?,?,?,?)";
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


			   /*
			   // Let us update age of the record with ID = 102;
			      int rows = stmt.executeUpdate();
			      System.out.println("Rows impacted : " + rows );
			      // Let us select all the records and display them.
			      sql = "SELECT id, name ,address, location FROM employeefc";
			      ResultSet rs = stmt.executeQuery(sql);
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			         //Retrieve by column name
			         int eid  = rs.getInt("id");
			         String name1 = rs.getString("name");
			         String address1 = rs.getString("address");
			         String location1 = rs.getString("location");
			         //Display values
			         System.out.print("ID: " + eid);
			         System.out.print(", Age: " + name1);
			         System.out.print(", First: " + address1);
			         System.out.println(", Last: " + location1);
			      }*/
            //STEP 6: Clean-up environment
            // rs.close();
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
        System.out.println("Professor Registered Successfuly!!");

    }

    public void approveStudentDAO(){


        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            // Step 3 Regiater Driver here and create connection

            Class.forName("com.mysql.jdbc.Driver");

            // Step 4 make/open  a connection

            // Connecting to database...;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            //Creating statement...;

            // Let us select all the records and display them.

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
                while (rs.next()) {
                    //Retrieve by column name


                    String eid = rs.getString("userId");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    System.out.print("Student ID: " + eid);
                    System.out.print(" Name: " + name);
                    System.out.println(" E-Mail: " + email);
                }


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

            String sql = "select name, studentId, email, departmentId from Student where gradeCardApproved = 0 "+
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
            String userId_of_approved_gradeCard = scanner.next();
            if (userId_of_approved_gradeCard.equals("#")) {
                return;
            }

            sql = "select grade, semesterId from `RegisteredCourse` where studentId = "+userId_of_approved_gradeCard;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            float gradeTotal = 0;
            int num = 0;
            String semID = rs.getString(2);
            boolean gradeNotAssigned = false;

            while(rs.next())
            {
                String tempgrade = rs.getString(1);
                if(tempgrade == null){
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

            sql = "select count(*) from `GradeCard`";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            int records = rs.getInt(1);
            rs.close();

            sql = "insert into `GradeCard` values ("+records+","+userId_of_approved_gradeCard+","+
                    gradeTotal / num +","+
                    semID+")";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            rs.close();

            System.out.println("GradeCard generated!");

        } catch (Exception e){
            System.out.println(e);
        }


    }









}