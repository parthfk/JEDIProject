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
        this.student = student;
        paymentDAO = PaymentDAOImpl.getInstance();
    }

    public double calculateAmount() {
        return 10000;
    }

    public boolean paymentApproved(Student student) {
        return true;
    }

    public void payCreditCard() {
        paymentDAO.payCreditCard(student);
    }

    public void payDebitCard() {
        paymentDAO.payDebitCard(student);
    }

    public void payNetBanking() {
        paymentDAO.payNetBanking(student);
    }

    public void payCash() {
        paymentDAO.payCash(student);
    }

    public void payCheque() {
        paymentDAO.payCheque(student);

    }

    public void sendNotification(String studentId, double paymentAmount, String paymentId, String message) {
        paymentDAO.sendNotification(studentId, paymentAmount, message);
    }
}
