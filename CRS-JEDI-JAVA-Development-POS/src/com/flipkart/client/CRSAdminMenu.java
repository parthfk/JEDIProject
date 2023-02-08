package com.flipkart.client;


import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Contains the functionality to display Admin Menu
 */
public class CRSAdminMenu {
    // 1. approve student
    // 2. add professor
    // 3. generate grade card
    // 4. add admin
    // 5. add course
    // 6. delete course
    // 7. logout
    private String adminUsername;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";



    Scanner in = new Scanner(System.in);
    UserService userService;

    /**
     * Constructor
     * @param admin
     */
    public CRSAdminMenu(Admin admin) {
        this.adminUsername = admin.getName();
        //displayMenu();

        userService = new UserServiceOperation();


        displayAdminMenu(admin);

    }

    /**
     * Method which implements different functions according to the option chosen
     * @param admin
     */
    private void displayAdminMenu(Admin admin) {
        while(true) {
            displayMenu();
            int input=in.nextInt();
            AdminService adminObj=new AdminServiceOperation();

            switch (input) {
                case 1:
                    adminObj.approveStudent();
                    break;
                case 2:
                    adminObj.addProfessor();
                    break;
                case 3:
                    //incomplete
                    adminObj.generateGradeCard();
                    break;
                case 4:
                    adminObj.addAdmin();
                    break;
                case 5:
                    adminObj.addCourse();
                    break;
                case 6:
                    adminObj.removeCourse();

                    break;
                case 7:
                    userService.viewCourseCatalogue(true);
                    break;
                case 8:
                    userService.logOut(admin);
                    System.out.println("Menu Exited");
                    return;
                default:
                    System.out.println(ANSI_YELLOW+
                            "Please select valid input"+
                            ANSI_RESET);
            }

        }
    }


    /**
     * Method which displays different options of admin menu
     */
    public void displayMenu() {

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Welcome to ADMIN portal, " + this.adminUsername);
        System.out.println("You have logged in successfully at time " + new Date());
        System.out.println("Press 1 to Approve student ");
        System.out.println("Press 2 to Add professor");
        System.out.println("Press 3 to Generate grade card");
        System.out.println("Press 4 to Add admin");
        System.out.println("Press 5 to Add course");
        System.out.println("Press 6 to Remove course");
        System.out.println("Press 7 to View Course Catalogue");
        System.out.println("Press 8 to Logout");
        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
    }
}
