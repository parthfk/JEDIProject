package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.service.StudentServiceOperation;
import com.flipkart.service.UserService;
import com.flipkart.service.UserServiceOperation;

import java.io.Console;
import java.util.Scanner;

public class CRSApplication {
    public static void main (String[] args) throws CourseNotFoundException {
        Scanner in = new Scanner(System.in);

        boolean endApplication  = false;
        UserService userService = new UserServiceOperation();
        User userObj = null;

        while (!endApplication) {
            System.out.println();
            System.out.println("**************************************************");
            System.out.println();
            System.out.println("Welcome to the CRS Application! choose the Option given below !" +
                    "\n  Main Menu :->\n 1. Login\n 2." +
                    " Registration of the Student\n " +
                    "3. update password\n 4. Exit\n");
            System.out.println();
            System.out.println("**************************************************");
            System.out.println();
            //endApplication = true;
            int optionSelected = in.nextInt();
            switch (optionSelected) {
                case 1:
                    userObj = userService.logIn();
                        if(userObj==null)
                        {
                            //System.out.println("Please contact Admin for approval");
                            continue;
                        }
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
                            continue;
                        default:
                            System.out.println("Please enter a valid Role.");
                            endApplication = false;
                            continue;
                    }
                    break;
                case 2:
                    System.out.println("Redirect to Student Registration");
                    new StudentServiceOperation().signup();
                    break;
                case 3:
                    System.out.println("Redirect to Update Password");
                    userService.updatePassword();
                    break;
                case 4:
                    endApplication=true;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select a valid input !");
                    endApplication = false;
            }
        }
    }
}
