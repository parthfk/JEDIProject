package com.flipkart.client;

import com.flipkart.bean.Student;
import com.flipkart.service.StudentService;
import com.flipkart.service.StudentServiceOperation;

import java.util.Scanner;

public class CRSStudentMenu {
    // 1. sem reg
    // 2. request grade card
    // 3. logout
    private String studentUsername;
    private StudentServiceOperation service;

    /**
     * constructor
     * @param student
     */
    public CRSStudentMenu(Student student) {
        this.studentUsername = student.getName();
        service = new StudentServiceOperation(student);
        displayMenu();
    }

    /**
     * Method which implements different functions according to the option chosen by the student
     */
    public void displayMenu() {
        boolean studentMenu = true;
        while (studentMenu) {
            System.out.println();
            System.out.println("**************************************************");
            System.out.println();
            System.out.println("Welcome to student portal, " + this.studentUsername);
            System.out.println("Press 1 to proceed with Sem Registration, view catalog, and pay fees");
            System.out.println("Press 2 to request grade card");
            System.out.println("Press 3 to logout");
            System.out.println();
            System.out.println("**************************************************");
            System.out.println();
            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
            switch (input) {
                case 1:
                    service.registerForSem();
                    break;
                case 2:
                    service.displayGradeCard();
                    break;
                case 3:
                    studentMenu = false;
                    return;
                default:
                    break;
            }
        }
    }
}
