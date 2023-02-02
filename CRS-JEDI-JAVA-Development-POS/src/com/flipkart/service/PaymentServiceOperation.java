package com.flipkart.service;


import com.flipkart.bean.Payment;
import com.flipkart.bean.PaymentNotification;

import java.util.Scanner;


public class PaymentServiceOperation implements PaymentService {

    private Scanner scanner;

    public PaymentServiceOperation() {
        scanner = new Scanner(System.in);
    }

    public double calculateAmount() {
        return 10000;
    }

    public boolean paymentApproved() {
    return true;
    }
    public void payCreditCard(){
        System.out.println("Please enter credit card number");
        String creditCardNumber = scanner.nextLine();

        System.out.println("Please enter cvv");
        String cvv = scanner.nextLine();

        System.out.println("Please enter expiry date");
        String exDate = scanner.nextLine();

    }
    public void payDebitCard(){
        System.out.println("Please enter debit card number");
        String debitCardNumber = scanner.nextLine();

        System.out.println("Please enter cvv");
        String cvv = scanner.nextLine();

        System.out.println("Please enter expiry date");
        String exDate = scanner.nextLine();
    }
    public void payUPI(){
        System.out.println("Please enter UPI ID");
        String upiId = scanner.nextLine();

    }
    public void payNetBanking(){
        System.out.println("Please enter bank name");
        String bankName = scanner.nextLine();

        System.out.println("Please enter account id");
        String accountId = scanner.nextLine();

        System.out.println("Please enter password");
        String password = scanner.nextLine();
    }

    public void payCash(){
        System.out.println("Please enter receipt number");
        String receiptNumber = scanner.nextLine();
    }

    public void payCheque(){
        System.out.println("Please enter bank name");
        String bankName = scanner.nextLine();

        System.out.println("Please enter Cheque Number");
        String chequeNumber = scanner.nextLine();

    }
    public void sendNotification(String studentId,double paymentAmount,String paymentId,String message){
        PaymentNotification notification = new PaymentNotification(studentId,paymentAmount,paymentId,message);
        System.out.println("Your transaction has been completed successfully!");
        System.out.println("--------------------------------------------------");
        System.out.println("Details of the transaction !");
        System.out.println("StudentID: "+notification.getStudentId());
        System.out.println("Payment Amount: "+notification.getPaymentAmount());
        System.out.println("PaymentID: "+notification.getPaymentAmount());
        System.out.println("Message: "+notification.getMessage());
    }
}
