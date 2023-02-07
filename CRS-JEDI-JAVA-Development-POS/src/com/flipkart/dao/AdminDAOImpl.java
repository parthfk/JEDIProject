package com.flipkart.dao;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminService;

import static com.flipkart.constant.DBConnection.*;

public class AdminDAOImpl implements AdminDAO{

    private  static int noOfUsers;
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
            stmt.setDate(2,new Date(336614400000L));   // this would set "DOB"
            stmt.setString(3, "address_0f_admin");
            stmt.setString(4, "1234567890");

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
            stmt.setDate(2,new Date(336614400000L));   // this would set "DOB"
            stmt.setString(3, "address_0f_admin");
            stmt.setString(4, "9112345678");
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
                String sql = "SELECT * FROM Student JOIN User ON Student.studentId = User.userId WHERE Student.statusApproval=false";

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

                sql = "UPDATE student SET statusApproval=true WHERE studentId=" + "'" + studentid + "'";
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

    @Override
    public void generateGradeCard(Student student) {

    }


}