//package com.flipkart.client;
//
//import com.flipkart.bean.Student;
//import com.flipkart.service.StudentService;
//import com.flipkart.service.StudentServiceOperation;
//
//import java.util.Scanner;
//import java.util.Date;
///**
// * Contains the functionality to display Student Menu
// */
//public class CRSStudentMenu {
//    // 1. sem reg
//    // 2. request grade card
//    // 3. logout
//    private Student student;
//    private String studentUsername;
//    private StudentServiceOperation service;
//
//    /**
//     * constructor
//     * @param student
//     */
//    public CRSStudentMenu(Student student) {
//        this.student = student;
//        this.studentUsername = this.student.getName();
//        service = new StudentServiceOperation(student);
//        displayMenu();
//    }
//
//    /**
//     * Method which implements different functions according to the option chosen by the student
//     */
//    public void displayMenu() {
//        boolean studentMenu = true;
//        while (studentMenu) {
//            System.out.println();
//            System.out.println("**************************************************");
//            System.out.println();
//            System.out.println("Welcome to STUDENT portal, " + this.studentUsername);
//            System.out.println("You have logged in successfully at time " + new Date());
//            System.out.println("Press 1 to Proceed with Sem Registration, view catalog, and pay fees");
//            System.out.println("Press 2 to Request grade card");
//            System.out.println("Press 3 to Logout");
//            System.out.println();
//            System.out.println("**************************************************");
//            System.out.println();
//            Scanner in = new Scanner(System.in);
//            int input = in.nextInt();
//            switch (input) {
//                case 1:
//                    service.registerForSem();
//                    break;
//                case 2:
//                    service.displayGradeCard();
//                    break;
//                case 3:
//                    studentMenu = false;
//                    service.logOut(student);
//                    return;
//                default:
//                    break;
//            }
//        }
//    }
//}
