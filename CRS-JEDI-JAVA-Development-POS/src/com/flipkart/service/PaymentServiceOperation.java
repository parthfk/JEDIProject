package com.flipkart.service;




import com.flipkart.bean.Payment;
import com.flipkart.bean.PaymentNotification;
import com.flipkart.bean.Student;
import com.flipkart.dao.PaymentDAOImpl;

import java.util.Scanner;


public class PaymentServiceOperation implements PaymentService {

    private Scanner scanner;
    private Student student;
    private PaymentDAOImpl paymentDAO;

    public PaymentServiceOperation(Student student) {
        scanner = new Scanner(System.in);
        this.student=student;
        paymentDAO =  PaymentDAOImpl.getInstance();
    }

    public double calculateAmount() {
        return 10000;
    }

    public boolean paymentApproved(Student student) {
        return true;
    }

    public void payCreditCard(){
//        System.out.println("Please enter credit card number");
//        String creditCardNumber = scanner.nextLine();
//
//        System.out.println("Please enter cvv");
//        String cvv = scanner.nextLine();
//
//        System.out.println("Please enter expiry date");
//        String exDate = scanner.nextLine();

        paymentDAO.payCreditCard(student);

    }
    public void payDebitCard(){
//        System.out.println("Please enter debit card number");
//        String debitCardNumber = scanner.nextLine();
//
//        System.out.println("Please enter cvv");
//        String cvv = scanner.nextLine();
//
//        System.out.println("Please enter expiry date");
//        String exDate = scanner.nextLine();

        paymentDAO.payDebitCard(student);
    }
    public void payUPI(){
//        System.out.println("Please enter UPI ID");
//        String upiId = scanner.nextLine();
        paymentDAO.payUPI(student);
    }
    public void payNetBanking(){
//        System.out.println("Please enter bank name");
//        String bankName = scanner.nextLine();
//
//        System.out.println("Please enter account id");
//        String accountId = scanner.nextLine();
//
//        System.out.println("Please enter password");
//        String password = scanner.nextLine();
        paymentDAO.payNetBanking(student);
    }

    public void payCash(){
//        System.out.println("Please enter receipt number");
//        String receiptNumber = scanner.nextLine();
        paymentDAO.payCash(student);
    }

    public void payCheque(){
//        System.out.println("Please enter bank name");
//        String bankName = scanner.nextLine();
//
//        System.out.println("Please enter Cheque Number");
//        String chequeNumber = scanner.nextLine();
        paymentDAO.payCheque(student);

    }
    public void sendNotification(String studentId,double paymentAmount,String paymentId,String message){
//        PaymentNotification notification = new PaymentNotification(studentId,paymentAmount,paymentId,message);
//        System.out.println("Your transaction has been completed successfully!");
//        System.out.println("--------------------------------------------------");
//        System.out.println("Details of the transaction !");
//        System.out.println("StudentID: "+notification.getStudentId());
//        System.out.println("Payment Amount: "+notification.getPaymentAmount());
//        System.out.println("PaymentID: "+notification.getPaymentAmount());
//        System.out.println("Message: "+notification.getMessage());

            paymentDAO.sendNotification(studentId, paymentAmount,paymentId,message);
    }
}
