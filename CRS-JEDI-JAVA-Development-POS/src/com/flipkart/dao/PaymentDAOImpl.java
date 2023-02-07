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
    private Connection conn = null;
    private PreparedStatement stmt = null;

    private PaymentDAOImpl() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static PaymentDAOImpl getInstance() {
        if (dao != null) return dao;
        return dao = new PaymentDAOImpl();
    }

    public String generatePaymentId()
    {
        try {
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
        }
            return Integer.toString(paymentID);
    }

    public boolean payCreditCard(Student student){
        boolean res=false;

        Scanner scanner=new Scanner(System.in);
        String paymentId=generatePaymentId();

        System.out.println("Please enter credit card number");
        String creditCardNumber = scanner.nextLine();
        System.out.println("Please enter cvv");
        String cvv = scanner.nextLine();
        System.out.println("Please enter expiry date");
        String exDate = scanner.nextLine();

        try{
            java.util.Date javaDate = new java.util.Date();
            java.sql.Date mySQLDate = new java.sql.Date(javaDate.getDate());

            String sql2  = "insert into Payment(paymentId,studentId,modeOfPayment,transactionDate) values("+paymentId+","+"'"+student.getUserId()+"'"+",4,"+mySQLDate.toString()+")";

            stmt = conn.prepareStatement(sql2);

            if(stmt.executeUpdate(sql2)==1){
                System.out.println("Insertion in Payment successful !");
            }else{
                System.out.println("Insertion in Payment unsuccessful !");
                return res;
            }

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,cardNumber,expiry,cvv) values("+paymentId+","+"'"+creditCardNumber+"'"+","+"'"+exDate+"'"+","+"'"+cvv+"'"+")";
            stmt = conn.prepareStatement(paymentDetailQuery);



            if(stmt.executeUpdate(paymentDetailQuery)==1){
                res=true;
                System.out.println("Insertion in PaymentDetails successful !");
            }else{
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }

        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean payDebitCard(Student student){
        boolean res=false;

        Scanner scanner=new Scanner(System.in);
        String paymentId=generatePaymentId();

        System.out.println("Please enter Debit card number");
        String DebitCardNumber = scanner.nextLine();
        System.out.println("Please enter cvv");
        String cvv = scanner.nextLine();
        System.out.println("Please enter expiry date");
        String exDate = scanner.nextLine();

        try{
            java.util.Date javaDate = new java.util.Date();
            java.sql.Date mySQLDate = new java.sql.Date(javaDate.getDate());

            String sql2  = "insert into Payment(paymentId,studentId,modeOfPayment,transactionDate) values("+paymentId+","+"'"+student.getUserId()+"'"+",5,"+mySQLDate.toString()+")";

            stmt = conn.prepareStatement(sql2);

            if(stmt.executeUpdate(sql2)==1){
                System.out.println("Insertion in Payment successful !");
            }else{
                System.out.println("Insertion in Payment unsuccessful !");
                return res;
            }

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,cardNumber,expiry,cvv) values("+paymentId+","+"'"+DebitCardNumber+"'"+","+"'"+exDate+"'"+","+"'"+cvv+"'"+")";
            stmt = conn.prepareStatement(paymentDetailQuery);



            if(stmt.executeUpdate(paymentDetailQuery)==1){
                 res=true;
                System.out.println("Insertion in PaymentDetails successful !");
            }else{
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }

        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }

    }
    public boolean payUPI(Student student){
        boolean res=false;

        Scanner scanner=new Scanner(System.in);

        String paymentId=generatePaymentId();

        System.out.println("Please enter Upi Id");
        String UPIId = scanner.nextLine();
        UPIId.trim();


        try{

            java.util.Date javaDate = new java.util.Date();
            java.sql.Date mySQLDate = new java.sql.Date(javaDate.getDate());

            String sql2  = "insert into Payment(paymentId,studentId,modeOfPayment,transactionDate) values("+paymentId+","+"'"+student.getUserId()+"'"+",3,"+mySQLDate.toString()+")";

            stmt = conn.prepareStatement(sql2);

            if(stmt.executeUpdate(sql2)==1){
                System.out.println("Insertion in Payment successful !");
            }else{
                System.out.println("Insertion in Payment unsuccessful !");
                return res;
            }

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,upiId) values("+paymentId+","+"'"+UPIId+"'"+")";
            stmt = conn.prepareStatement(paymentDetailQuery);


            if(stmt.executeUpdate(paymentDetailQuery)==1){
                res=true;

                System.out.println("Insertion in PaymentDetails successful !");
            }else{
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }

        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }

    }
    public boolean payNetBanking(Student student){
        boolean res=false;

        Scanner scanner=new Scanner(System.in);
        String paymentId=generatePaymentId();
        System.out.println("Please enter account number");
        String accountNumber = scanner.nextLine();
        System.out.println("Please enter your bank name");
        String bankName = scanner.nextLine();
        System.out.println("please enter your password");
        String password = scanner.nextLine();

        try{

            java.util.Date javaDate = new java.util.Date();
            java.sql.Date mySQLDate = new java.sql.Date(javaDate.getDate());

            String sql2  = "insert into Payment(paymentId,studentId,modeOfPayment,transactionDate) values("+paymentId+","+student.getUserId()+",6,"+mySQLDate.toString()+")";

            stmt = conn.prepareStatement(sql2);

            if(stmt.executeUpdate(sql2)==1){
                System.out.println("Insertion in Payment successful !");
            }else{
                System.out.println("Insertion in Payment unsuccessful !");
                return res;
            }

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,bankName,accountId,password) values("+paymentId+","+"'"+bankName+"'"+","+"'"+accountNumber+"'"+","+"'"+password+"'"+")";

            stmt = conn.prepareStatement(paymentDetailQuery);


            if(stmt.executeUpdate(paymentDetailQuery)==1){
                res=true;

                System.out.println("Insertion in PaymentDetails successful !");
            }else{
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }

        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }
    }
    public boolean payCash(Student student){
        boolean res=false;

        String paymentId=generatePaymentId();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter receipt number");
        String receiptNumber = scanner.nextLine();

        try{
            java.util.Date javaDate = new java.util.Date();
            java.sql.Date mySQLDate = new java.sql.Date(javaDate.getDate());

            String sql2  = "insert into Payment(paymentId,studentId,modeOfPayment,transactionDate) values("+paymentId+","+student.getUserId()+",1,"+mySQLDate.toString()+")";

            stmt = conn.prepareStatement(sql2);

            if(stmt.executeUpdate(sql2)==1){
                System.out.println("Insertion in Payment successful !");
            }else{
                System.out.println("Insertion in Payment unsuccessful !");
                return res;
            }

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,receiptNumber) values("+paymentId+","+"'"+receiptNumber+"'"+")";

            stmt = conn.prepareStatement(paymentDetailQuery);


            if(stmt.executeUpdate(paymentDetailQuery)==1){
                res=true;

                System.out.println("Insertion in PaymentDetails successful !");
            }else{
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }

    }

    public boolean payCheque(Student student){
        boolean res=false;

        Scanner scanner=new Scanner(System.in);
        String paymentId=generatePaymentId();
        System.out.println("Please enter cheque number");
        String chequeNumber = scanner.nextLine();
        System.out.println("Please enter receipt number");
        String receiptNumber = scanner.nextLine();

        try{
            java.util.Date javaDate = new java.util.Date();
            java.sql.Date mySQLDate = new java.sql.Date(javaDate.getDate());
            String sql2  = "insert into Payment(paymentId,studentId,modeOfPayment,transactionDate) values("+paymentId+","+student.getUserId()+",2,"+mySQLDate.toString()+")";

            stmt = conn.prepareStatement(sql2);

            if(stmt.executeUpdate(sql2)==1){
                System.out.println("Insertion in Payment successful !");
            }else{
                System.out.println("Insertion in Payment unsuccessful !");
                return res;
            }


            String paymentDetailQuery = "insert into PaymentDetails(paymentId,chequeNumber,receiptNumber) values("+paymentId+","+"'"+chequeNumber+"'"+","+"'"+receiptNumber+"')";
            stmt = conn.prepareStatement(paymentDetailQuery);


            if(stmt.executeUpdate(paymentDetailQuery)==1){
                res=true;
                System.out.println("Insertion in PaymentDetails successful !");
            }else{
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }


            if(stmt.executeUpdate(paymentDetailQuery)==1){
                System.out.println("Insertion in PaymentDetails successful !");
            }else{
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally {
            return res;
        }

    }

    public void sendNotification(String studentId,double paymentAmount,String message)
    {
        try{
            String paymentId=Integer.toString(Integer.parseInt(generatePaymentId())-1);
            String insertPaymentNotificationQuery = "insert into PaymentNotification(notificationId,studentId,paymentAmount,message) values ("+paymentId+","+studentId+","+paymentAmount+",'"+message+"')";
            stmt = conn.prepareStatement(insertPaymentNotificationQuery);


            if(stmt.executeUpdate()==1){
                System.out.println("Insertion in PaymentNotification successful !");
            }
            else {
                System.out.println("Insertion in PaymentNotification failed!");
                return;
            }

            String fetchquery = "SELECT studentId, notificationId ,paymentAmount, message FROM PaymentNotification where studentId= "+studentId;
            stmt = conn.prepareStatement(fetchquery);

//            stmt.setString(1,studentId);
            ResultSet rs = stmt.executeQuery(fetchquery);

            if(rs.next()) {
                String studentId1 = rs.getString("studentId");
                String paymentId1 = rs.getString("notificationId");
                double amountPaid = rs.getDouble("paymentAmount");
                String message1 = rs.getString("message");

                System.out.println("Student Id:" + studentId1);
                System.out.println("Payment Id:" + paymentId1);
                System.out.println("Amount Paid:" + amountPaid);
                System.out.println(message);
            }
            else{
                System.out.println("Reading from Payment Notification failed !");
            }
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
        try{
            String studentId=student.getUserId();
            boolean feeDone=true;

            String setFeeDoneQuery = "UPDATE Student SET feeDone = 1 WHERE studentId = "+studentId;
            stmt = conn.prepareStatement(setFeeDoneQuery);
//            stmt.setString(1,studentId);

            int m = stmt.executeUpdate(setFeeDoneQuery);
            if (m == 1){
                System.out.println("FeeDone Updated successfully");
                return true;
            }
            else {
                System.out.println("Update failed");
            }
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return false;
    }
}
