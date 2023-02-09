package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.flipkart.constant.DBConnection;
import com.flipkart.constant.PaymentMode;

import static com.flipkart.constant.DBConnection.*;

import com.flipkart.bean.Student;
import com.flipkart.constant.PaymentMode;
import com.flipkart.utils.DbConnection;

public class PaymentDAOImpl implements PaymentDAO {
    private int paymentID = 1;
    private static PaymentDAOImpl dao;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public PaymentDAOImpl() {
        conn = DbConnection.getInstance().getConnection();
    }

    public static PaymentDAOImpl getInstance() {
        if (dao != null) return dao;
        return dao = new PaymentDAOImpl();
    }

    public void insertHelper(String paymentId,String studentId,Integer modeOfPayment)
    {
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getDate());

        String sql2 = "insert into Payment(paymentId,studentId,modeOfPayment,transactionDate) values(" + paymentId + "," + "'" + studentId + "'" + ",'"+modeOfPayment+"','" + mySQLDate.toString() + "')";
        System.out.println(sql2);

        try {
            stmt = conn.prepareStatement(sql2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            if (stmt.executeUpdate(sql2) == 1) {
                    System.out.println("Insertion in Payment successful !");
                } else {
                    System.out.println("Insertion in Payment unsuccessful !");
                    return ;
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public String generatePaymentId() {
        try {
            String sql = "select count(*) from Payment";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next())
                paymentID = rs.getInt("COUNT(*)") + 1;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Integer.toString(paymentID);
    }

    public boolean payCreditCard(Student student) {
        boolean res = false;

        Scanner scanner = new Scanner(System.in);
        String paymentId = generatePaymentId();

        System.out.println("Please enter credit card number");
        String creditCardNumber = scanner.nextLine();
        System.out.println("Please enter cvv");
        String cvv = scanner.nextLine();
        System.out.println("Please enter expiry date");
        String exDate = scanner.nextLine();
        Integer modeOfPayment=PaymentMode.CREDIT_CARD.ordinal();

        try {
            insertHelper(paymentId,student.getUserId(),modeOfPayment);

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,cardNumber,expiry,cvv) values(" + paymentId + "," + "'" + creditCardNumber + "'" + "," + "'" + exDate + "'" + "," + "'" + cvv + "'" + ")";
            stmt = conn.prepareStatement(paymentDetailQuery);

            if (stmt.executeUpdate(paymentDetailQuery) == 1) {
                res = true;
                System.out.println("Insertion in PaymentDetails successful !");
            } else {
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean payDebitCard(Student student) {
        boolean res = false;

        Scanner scanner = new Scanner(System.in);
        String paymentId = generatePaymentId();

        System.out.println("Please enter Debit card number");
        String DebitCardNumber = scanner.nextLine();
        System.out.println("Please enter cvv");
        String cvv = scanner.nextLine();
        System.out.println("Please enter expiry date");
        String exDate = scanner.nextLine();
        Integer modeOfPayment=PaymentMode.DEBIT_CARD.ordinal();

        try {

            insertHelper(paymentId,student.getUserId(),modeOfPayment);

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,cardNumber,expiry,cvv) values(" + paymentId + "," + "'" + DebitCardNumber + "'" + "," + "'" + exDate + "'" + "," + "'" + cvv + "'" + ")";
            stmt = conn.prepareStatement(paymentDetailQuery);

            if (stmt.executeUpdate(paymentDetailQuery) == 1) {
                res = true;

                System.out.println("Insertion in PaymentDetails successful !");
            } else {
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }

    }

    public boolean payUPI(String studentId, String upiId) {
        boolean res = false;

        String paymentId = generatePaymentId();

        upiId.trim();
        Integer modeOfPayment=3;


        try {

            insertHelper(paymentId,studentId,modeOfPayment);

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,upiId) values(" + paymentId + "," + "'" + upiId + "'" + ")";
            stmt = conn.prepareStatement(paymentDetailQuery);


            if (stmt.executeUpdate(paymentDetailQuery) == 1) {
                res = true;

                System.out.println("Insertion in PaymentDetails successful !");
            } else {
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }

    }

    public boolean payNetBanking(Student student) {
        boolean res = false;

        Scanner scanner = new Scanner(System.in);
        String paymentId = generatePaymentId();
        System.out.println("Please enter account number");
        String accountNumber = scanner.nextLine();
        System.out.println("Please enter your bank name");
        String bankName = scanner.nextLine();
        System.out.println("please enter your password");
        String password = scanner.nextLine();
        Integer modeOfPayment=PaymentMode.NET_BANKING.ordinal();

        try {

            insertHelper(paymentId,student.getUserId(),modeOfPayment);

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,bankName,accountId,password) values(" + paymentId + "," + "'" + bankName + "'" + "," + "'" + accountNumber + "'" + "," + "'" + password + "'" + ")";

            stmt = conn.prepareStatement(paymentDetailQuery);


            if (stmt.executeUpdate(paymentDetailQuery) == 1) {
                res = true;

                System.out.println("Insertion in PaymentDetails successful !");
            } else {
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean payCash(Student student) {
        boolean res = false;

        String paymentId = generatePaymentId();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter receipt number");
        String receiptNumber = scanner.nextLine();
        Integer modeOfPayment=PaymentMode.CASH.ordinal();

        try {
            insertHelper(paymentId,student.getUserId(),modeOfPayment);

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,receiptNumber) values(" + paymentId + "," + "'" + receiptNumber + "'" + ")";

            stmt = conn.prepareStatement(paymentDetailQuery);


            if (stmt.executeUpdate(paymentDetailQuery) == 1) {
                res = true;

                System.out.println("Insertion in PaymentDetails successful !");
            } else {
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }

    }

    public boolean payCheque(Student student) {
        boolean res = false;

        Scanner scanner = new Scanner(System.in);
        String paymentId = generatePaymentId();
        System.out.println("Please enter cheque number");
        String chequeNumber = scanner.nextLine();
        System.out.println("Please enter receipt number");
        String receiptNumber = scanner.nextLine();
        Integer modeOfPayment=PaymentMode.CHEQUE.ordinal();

        try {
            insertHelper(paymentId,student.getUserId(),modeOfPayment);

            String paymentDetailQuery = "insert into PaymentDetails(paymentId,chequeNumber,receiptNumber) values(" + paymentId + "," + "'" + chequeNumber + "'" + "," + "'" + receiptNumber + "')";
            stmt = conn.prepareStatement(paymentDetailQuery);


            if (stmt.executeUpdate(paymentDetailQuery) == 1) {
                System.out.println("Insertion in PaymentDetails successful !");
            } else {
                System.out.println("Insertion in PaymentDetails unsuccessful !");
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return res;
        }

    }

    public void sendNotification(String studentId, double paymentAmount, String message) {
        try {
            String paymentId = Integer.toString(Integer.parseInt(generatePaymentId()) - 1);
            String insertPaymentNotificationQuery = "insert into PaymentNotification(notificationId,studentId,paymentAmount,message) values (" + paymentId + ",'" + studentId + "'," + paymentAmount + ",'" + message + "')";
            stmt = conn.prepareStatement(insertPaymentNotificationQuery);


            if (stmt.executeUpdate() == 1) {
                System.out.println("Insertion in PaymentNotification successful !");
            } else {
                System.out.println("Insertion in PaymentNotification failed!");
                return;
            }


            String fetchquery = "SELECT studentId, notificationId ,paymentAmount, message FROM PaymentNotification where studentId= '" + studentId + "'";

            stmt = conn.prepareStatement(fetchquery);

            ResultSet rs = stmt.executeQuery(fetchquery);

            if (rs.next()) {
                String studentId1 = rs.getString("studentId");
                String paymentId1 = rs.getString("notificationId");
                double amountPaid = rs.getDouble("paymentAmount");
                String message1 = rs.getString("message");

                System.out.println("Student Id:" + studentId1);
                System.out.println("Payment Id:" + paymentId1);
                System.out.println("Amount Paid:" + amountPaid);
                System.out.println(message);
            } else {
                System.out.println("Reading from Payment Notification failed !");
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

    }


    public boolean paymentApproved(Student student) {
        try {
            String studentId = student.getUserId();
            boolean feeDone = true;


            String setFeeDoneQuery = "UPDATE Student SET feeDone = 1 WHERE studentId = '" + studentId + "'";

            stmt = conn.prepareStatement(setFeeDoneQuery);

            int m = stmt.executeUpdate(setFeeDoneQuery);
            if (m == 1) {
                System.out.println("FeeDone Updated successfully");
                return true;
            } else {
                System.out.println("Update failed");
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return false;
    }
}
