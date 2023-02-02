package com.flipkart.client;

import com.flipkart.bean.Professor;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceOperation;
import com.flipkart.service.UserService;
import com.flipkart.service.UserServiceOperation;

import java.util.Scanner;

public class CRSProfessorMenu {
    // 1. select courses to teach
    // 2. view course catalog
    // 3. view enrolled students
    // 4. add grade
    // 5. logout
    private String professorUsername;
    private Scanner scanner;
    private UserService userService;

    public CRSProfessorMenu(Professor professor) {
        this.professorUsername = professor.getName();
        scanner = new Scanner(System.in);
        userService = new UserServiceOperation();
        displayMenu(professor);
    }

    public void displayMenu(Professor professor) {
        System.out.println("Welcome to professor portal -> " + this.professorUsername);
        System.out.println("Press 1 to select courses to teach");
        System.out.println("Press 2 to view course catalog");
        System.out.println("Press 3 to view enrolled students");
        System.out.println("Press 4 to add grades");
        System.out.println("Press 5 to logout");

        while(true) {
            int input=scanner.nextInt();
            ProfessorService profService = new ProfessorServiceOperation();

            switch (input) {
                case 1:
                    profService.approveStudent();
                    break;
                case 2:
                    profService.addProfessor();
                    break;
                case 3:
                    //incomplete
                    profService.generateGradeCard();
                    break;
                case 4:
                    profService.addAdmin();
                    break;
                case 5:
                    userService.logOut();
                    System.out.println("Menu Exited");
                    return;
                default:
                    System.out.println("Please select valid input");
            }
        }
    }
}
