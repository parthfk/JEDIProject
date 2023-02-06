package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.flipkart.constant.PaymentMode;

import static com.flipkart.constant.DBConnection.*;
import com.flipkart.bean.Student;
import com.flipkart.constant.PaymentMode;

public class PaymentDAOImpl implements PaymentDAO{

    private int paymentID=1;
    private static PaymentDAOImpl dao;

    private PaymentDAOImpl() {

    }
    public static PaymentDAOImpl getInstance() {
        if (dao != null) return dao;
        return dao = new PaymentDAOImpl();
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
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally {
            return Integer.toString(paymentID);
        }
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

            stmt.setString(1, paymentId );
            stmt.setString(2, studentId);
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

            Date myDate = new Date();
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dmy = dmyFormat.format(myDate);
            stmt.setString(1, paymentId);
            stmt.setString(2, student.getUserId());
            stmt.setString(3, String.valueOf(PaymentMode.CREDIT_CARD));
            stmt.setDate(4, java.sql.Date.valueOf(dmy));
            stmt.executeUpdate(sql);

            String paymentDetailQuery = "insert into PaymentDetails values(?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(paymentDetailQuery);

            stmt.setString(1, paymentId);
            stmt.setString(2, creditCardNumber);
            stmt.setString(3, exDate);
            stmt.setString(4, cvv);
            stmt.setString(5, null);
            stmt.setString(6, null);
            stmt.setString(7, null);
            stmt.setString(8, null);
            stmt.setString(9, null);
            stmt.setString(10,null);
            stmt.executeUpdate(paymentDetailQuery);

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

    public void payDebitCard(Student student){

        Scanner scanner=new Scanner(System.in);
        String paymentId=generatePaymentId();

        System.out.println("Please enter Debit card number");
        String DebitCardNumber = scanner.nextLine();
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

            Date myDate = new Date();
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dmy = dmyFormat.format(myDate);
            stmt.setString(1, paymentId);
            stmt.setString(2, student.getUserId());
            stmt.setString(3, String.valueOf(PaymentMode.DEBIT_CARD));
            stmt.setDate(4, java.sql.Date.valueOf(dmy));
            stmt.executeUpdate(sql);

            String paymentDetailQuery = "insert into PaymentDetails values(?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(paymentDetailQuery);

            stmt.setString(1, paymentId);
            stmt.setString(2, DebitCardNumber);
            stmt.setString(3, exDate);
            stmt.setString(4, cvv);
            stmt.setString(5, null);
            stmt.setString(6, null);
            stmt.setString(7, null);
            stmt.setString(8, null);
            stmt.setString(9, null);
            stmt.setString(10,null);
            stmt.executeUpdate(paymentDetailQuery);

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
    public void payUPI(Student student){

        Scanner scanner=new Scanner(System.in);

        String paymentId=generatePaymentId();

        System.out.println("Please enter Upi Id");
        String UPIId = scanner.nextLine();

        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql  = "insert into Payment values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            Date myDate = new Date();
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dmy = dmyFormat.format(myDate);
            stmt.setString(1, paymentId);
            stmt.setString(2, student.getUserId());
            stmt.setString(3, String.valueOf(PaymentMode.UPI));
            stmt.setDate(4, java.sql.Date.valueOf(dmy));
            stmt.executeUpdate(sql);

            String paymentDetailQuery = "insert into PaymentDetails values(?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(paymentDetailQuery);

            stmt.setString(1, paymentId);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, null);
            stmt.setString(5, UPIId);
            stmt.setString(6, null);
            stmt.setString(7, null);
            stmt.setString(8, null);
            stmt.setString(9, null);
            stmt.setString(10,null);
            stmt.executeUpdate(paymentDetailQuery);

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
    public void payNetBanking(Student student){

        Scanner scanner=new Scanner(System.in);
        String paymentId=generatePaymentId();
        System.out.println("Please enter account number");
        String accountNumber = scanner.nextLine();
        System.out.println("Please enter your bank name");
        String bankName = scanner.nextLine();
        System.out.println("please enter your password");
        String password = scanner.nextLine();

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql  = "insert into Payment values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            Date myDate = new Date();
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dmy = dmyFormat.format(myDate);
            stmt.setString(1, paymentId);
            stmt.setString(2, student.getUserId());
            stmt.setString(3, String.valueOf(PaymentMode.NET_BANKING));
            stmt.setDate(4, java.sql.Date.valueOf(dmy));
            stmt.executeUpdate(sql);

            String paymentDetailQuery = "insert into PaymentDetails values(?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(paymentDetailQuery);

            stmt.setString(1, paymentId);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, null);
            stmt.setString(5, null);
            stmt.setString(6, bankName);
            stmt.setString(7, accountNumber);
            stmt.setString(8, password);
            stmt.setString(9, null);
            stmt.setString(10,null);
            stmt.executeUpdate(paymentDetailQuery);

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
    public void payCash(Student student){

        String paymentId=generatePaymentId();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter receipt number");
        String receiptNumber = scanner.nextLine();

        Connection conn = null;
        PreparedStatement stmt = null;
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql  = "insert into Payment values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            Date myDate = new Date();
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dmy = dmyFormat.format(myDate);
            stmt.setString(1, paymentId);
            stmt.setString(2, student.getUserId());
            stmt.setString(3, String.valueOf(PaymentMode.CASH));
            stmt.setDate(4, java.sql.Date.valueOf(dmy));
            stmt.executeUpdate(sql);

            String paymentDetailQuery = "insert into PaymentDetails values(?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(paymentDetailQuery);

            stmt.setString(1, paymentId);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, null);
            stmt.setString(5, null);
            stmt.setString(6, null);
            stmt.setString(7, null);
            stmt.setString(8, null);
            stmt.setString(9, null);
            stmt.setString(10,receiptNumber);
            stmt.executeUpdate(paymentDetailQuery);

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


    public void payCheque(Student student){

        Scanner scanner=new Scanner(System.in);
        String paymentId=generatePaymentId();
        System.out.println("Please enter cheque number");
        String chequeNumber = scanner.nextLine();
        System.out.println("Please enter receipt number");
        String receiptNumber = scanner.nextLine();

        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql  = "insert into Payment values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            Date myDate = new Date();
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dmy = dmyFormat.format(myDate);
            stmt.setString(1, paymentId);
            stmt.setString(2, student.getUserId());
            stmt.setString(3, String.valueOf(PaymentMode.CHEQUE));
            stmt.setDate(4, java.sql.Date.valueOf(dmy));
            stmt.executeUpdate(sql);

            String paymentDetailQuery = "insert into PaymentDetails values(?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(paymentDetailQuery);

            stmt.setString(1, paymentId);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, null);
            stmt.setString(5, null);
            stmt.setString(6, null);
            stmt.setString(7, null);
            stmt.setString(8, null);
            stmt.setString(9, chequeNumber);
            stmt.setString(10,receiptNumber);
            stmt.executeUpdate(paymentDetailQuery);

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
