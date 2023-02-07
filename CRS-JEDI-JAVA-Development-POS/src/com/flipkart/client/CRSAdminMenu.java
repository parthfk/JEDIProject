package com.flipkart.client;


import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.service.*;
import com.flipkart.data.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CRSAdminMenu {
    // 1. approve student
    // 2. add professor
    // 3. generate grade card
    // 4. add admin
    // 5. add course
    // 6. delete course
    // 7. logout
    private String adminUsername;



    Scanner in = new Scanner(System.in);
    UserService userService;

    public CRSAdminMenu(Admin admin) throws CourseNotFoundException {
        this.adminUsername = admin.getName();
        //displayMenu();

        userService = new UserServiceOperation();


        displayAdminMenu(admin);

    }

    private void displayAdminMenu(Admin admin) throws CourseNotFoundException {
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
                    try{
                        adminObj.removeCourse();
                    }catch(CourseNotFoundException e){
                        System.out.println("Error :"+ e.getMessage());
                    }

                    break;
                case 7:
                    userService.viewCourseCatalogue();
                    break;
                case 8:
                    userService.logOut(admin);
                    System.out.println("Menu Exited");
                    return;
                default:
                    System.out.println("Please select valid input");
            }

        }
    }



    public void displayMenu() {

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Welcome to admin portal, " + this.adminUsername);
        System.out.println("Press 1 to approve student ");
        System.out.println("Press 2 to add professor");
        System.out.println("Press 3 to generate grade card");
        System.out.println("Press 4 to add admin");
        System.out.println("Press 5 to add course");
        System.out.println("Press 6 to remove course");
        System.out.println("Press 7 to View Course Catalogue");
        System.out.println("Press 8 to logout");
        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
    }
}
