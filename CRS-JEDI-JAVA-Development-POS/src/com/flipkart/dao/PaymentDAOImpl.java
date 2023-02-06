package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Date;

import static com.flipkart.constant.DBConnection.*;
import com.flipkart.bean.Student;
import com.flipkart.constant.PaymentMode;

public class PaymentDAOImpl implements PaymentDAO{

    private int paymentID=1;
    private static PaymentDAOImpl dao;

    private PaymentDAOImpl() {

    }

    public String generatePaymentId()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select count(*) from Payment";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next())
                paymentID = rs.getInt("COUNT(*)")+1;
            return Integer.toString(paymentID);
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return "";
    }

    public static PaymentDAOImpl getInstance() {
        if (dao != null) return dao;
        return dao = new PaymentDAOImpl();
    }

    public void sendNotification(String studentId,double paymentAmount,String paymentId,String message)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String insertPaymentNotificationQuery = "insert into PaymentNotification values(?,?,?,?)";
            stmt = conn.prepareStatement(insertPaymentNotificationQuery);

            stmt.setString(1, studentId);
            stmt.setString(2, paymentId);
            stmt.setDouble(3, paymentAmount);
            stmt.setString(4, message);
            stmt.executeUpdate();

            String fetchquery = "SELECT studentId, paymentId ,paymentAmount, message FROM PaymentNotification where studentId="+studentId;
            ResultSet rs = stmt.executeQuery(fetchquery);

            String studentId1  = rs.getString("studentId");
            String paymentId1= rs.getString("paymentId");
            double amountPaid  = rs.getDouble("paymentAmount");
            String message1 = rs.getString("message");

            System.out.println("Student Id:"+studentId1);
            System.out.println("Payment Id:"+paymentId1);
            System.out.println("Amount Paid:"+amountPaid);
            System.out.println(message);

            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

    }



    public void payCreditCard(Student student){

        Scanner scanner=new Scanner(System.in);
        String paymentId=generatePaymentId();
        System.out.println("Please enter credit card number");
        String creditCardNumber = scanner.nextLine();

        System.out.println("Please enter cvv");
        String cvv = scanner.nextLine();

        System.out.println("Please enter expiry date");
        String exDate = scanner.nextLine();

        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql  = "insert into Payment values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, paymentId);
            stmt.setString(2, student.getUserId());
            stmt.setInt(3, 2);
            stmt.setString(4,"" );
            stmt.executeUpdate(sql);

            String fetchquery = "SELECT studentId, paymentId ,paymentAmount, message FROM PaymentNotification where studentId="+student.getUserId();
            ResultSet rs = stmt.executeQuery(fetchquery);

            String studentId1  = rs.getString("studentId");
            String paymentId1= rs.getString("paymentId");
            double amountPaid  = rs.getDouble("paymentAmount");
            String message1 = rs.getString("message");


            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public void payDebitCard(){

    }
    public void payUPI(){

    }
    public void payNetBanking(){

    }
    public void payCash(){

    }
    public void payCheque(){

    }

    public boolean paymentApproved(Student student){

        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String studentId=student.getUserId();
            boolean feeDone=true;

            String setFeeDoneQuery = "\"UPDATE Student SET feeDone=\"+ feeDone + \" WHERE studentId=\"+ studentId";
            stmt = conn.prepareStatement(setFeeDoneQuery);

            int m = stmt.executeUpdate(setFeeDoneQuery);
            if (m == 1){
                System.out.println("FeeDone Updated successfully");
            }
            else System.out.println("Update failed");
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return true;
    }
}
