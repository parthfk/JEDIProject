package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.service.UserService;
import com.flipkart.service.UserServiceOperation;

import java.io.Console;
import java.util.Scanner;

public class CRSApplication {
//    public static void login() {
//        Scanner in = new Scanner(System.in);
//        Console pIn = System.console();
//
//        System.out.println("Please enter your username");
//        String userName = in.next();
//
//        System.out.println("Please enter your Password");
//        String password = in.next();
//
//        //Insert Authentication here
//
//        boolean endApplication  = false;
//
//        while(!endApplication) {
//            endApplication = true;
//
//            System.out.println("Please enter your Role: ");
//            String role = in.next().toLowerCase();
//
//            switch (role) {
//                case "student":
//                    CRSStudentMenu studentMenu = new CRSStudentMenu(userName);
//                    break;
//                case "professor":
//                    CRSProfessorMenu professorMenu = new CRSProfessorMenu(userName);
//                    break;
//                case "admin":
//                    CRSAdminMenu adminMenu = new CRSAdminMenu(userName);
//                    break;
//                default:
//                    System.out.println("Please enter a valid Role.");
//                    endApplication = false;
//                    break;
//            }
//        }
//    }

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the CRS Application! choose the Option given below !" +
                 "\n  Main Menu :->\n 1. Login\n 2." +
                 " Registration of the Student\n " +
                 "3. update password\n 4. Exit\n");

        boolean endApplication  = false;
        UserService userService = new UserServiceOperation();
        User userObj = null;

        while (!endApplication) {
            endApplication = true;
            int optionSelected = in.nextInt();
            switch (optionSelected) {
                case 1:
                    userObj = userService.logIn();
                    String role = userObj.getUserType().toLowerCase();
                    switch (role) {
                        case "student":
                            CRSStudentMenu studentMenu = new CRSStudentMenu((Student)userObj);
                            break;
                        case "professor":
                            CRSProfessorMenu professorMenu = new CRSProfessorMenu((Professor) userObj);
                            break;
                        case "admin":
                            CRSAdminMenu adminMenu = new CRSAdminMenu((Admin) userObj);
                            break;
                        default:
                            System.out.println("Please enter a valid Role.");
                            endApplication = false;
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Redirect to Student Registration");
                    break;
                case 3:
                    System.out.println("Redirect to Update Password");
                    userService.updatePassword();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select a valid input !");
                    endApplication = false;
            }
        }
    }
}
