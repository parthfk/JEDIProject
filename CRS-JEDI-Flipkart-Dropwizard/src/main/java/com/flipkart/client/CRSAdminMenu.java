//package com.flipkart.client;
//
//
//import com.flipkart.bean.Admin;
//import com.flipkart.bean.Course;
//import com.flipkart.bean.Professor;
//import com.flipkart.bean.Student;
//import com.flipkart.exception.CourseAlreadyRegisteredException;
//import com.flipkart.exception.CourseNotFoundException;
//import com.flipkart.service.*;
//import com.flipkart.utils.Utils;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Scanner;
//
///**
// * Contains the functionality to display Admin Menu
// */
//public class CRSAdminMenu {
//    // 1. approve student
//    // 2. add professor
//    // 3. generate grade card
//    // 4. add admin
//    // 5. add course
//    // 6. delete course
//    // 7. logout
//    private String adminUsername;
//
//
//
//    Scanner in = new Scanner(System.in);
//    UserService userService;
//    private Scanner scanner;
//    /**
//     * Constructor
//     * @param admin
//     */
//    public CRSAdminMenu(Admin admin) {
//        this.adminUsername = admin.getName();
//        //displayMenu();
//
//        userService = new UserServiceOperation();
//
//        Scanner scanner = new Scanner(System.in);
//        //displayAdminMenu(admin);
//
//    }
//
//    /**
//     * Method which implements different functions according to the option chosen
//     * @param admin
//     */
//    private void displayAdminMenu(Admin admin) throws CourseAlreadyRegisteredException,Exception {
//        while(true) {
//            displayMenu();
//            int input=in.nextInt();
//            AdminService adminObj=new AdminServiceOperation();
//
//            switch (input) {
//                case 1:
//                        System.out.println("Enter student ID to be Approved or Press # to exit");
//                        Scanner sc = new Scanner(System.in);
//                        String studentId = sc.next();
//                        if (studentId.equals("#")) {
//                            break;
//                        }
//                    adminObj.approveStudent(studentId);
//                    break;
//                case 2:
//                    Professor newProf = new Professor();
//                    System.out.println("Enter new Professor Name");
//                    newProf.setName(scanner.next());
//                    System.out.println("Enter new Professor Email");
//                    newProf.setEmail(scanner.next());
//                    System.out.println("Enter new Professor Password");
//                    newProf.setPassword(scanner.next());
//                    System.out.println("Enter new Professor DepartmentID");
//                    newProf.setDepartmentID(scanner.next());
//                    newProf.setUserType("professor");
//                    System.out.println("Enter professor's address");
//                    String address = scanner.next();
//                    newProf.setAddress(address);
//                    System.out.println("Enter professor's mobile number");
//                    String mobileNumber = scanner.next();
//                    while (!Utils.isPhoneNumberValid(mobileNumber)) {
//                        System.out.println("Your mobile number is invalid. It must a 10 digit numeric. Please enter again");
//                        mobileNumber = scanner.nextLine();
//                    }
//                    newProf.setMobileNumber(mobileNumber);
//                    System.out.println("Enter your date of birth in the format 'YYYY-MM-DD' ONLY");
//                    java.sql.Date dobParsed = Utils.isDateValid(scanner);
//                    newProf.setDob(dobParsed);
//                    adminObj.addProfessor(newProf);
//                    break;
//                case 3:
//                    //incomplete
//                    String userId_of_approved_gradeCard = scanner.next();
//                    adminObj.generateGradeCard(userId_of_approved_gradeCard);
//                    break;
//                case 4:
//                    Admin newAdmin = new Admin();
//                    System.out.println("Enter new Admin Name");
//                    newAdmin.setName(scanner.next());
//                    System.out.println("Enter new Admin Email");
//                    newAdmin.setEmail(scanner.next());
//                    newAdmin.setUserType("admin");
//                    System.out.println("Enter new Admin Password");
//                    newAdmin.setPassword(scanner.next());
//                    System.out.println("Enter new admin's address");
//                    address = scanner.next();
//                    newAdmin.setAddress(address);
//                    System.out.println("Enter new admin's number");
//                    mobileNumber = scanner.next();
//                    while (!Utils.isPhoneNumberValid(mobileNumber)) {
//                        System.out.println("Your mobile number is invalid. It must a 10 digit numeric. Please enter again");
//                        mobileNumber = scanner.nextLine();
//                    }
//                    newAdmin.setMobileNumber(mobileNumber);
//                    System.out.println("Enter your date of birth in the format 'YYYY-MM-DD' ONLY");
//                    dobParsed = Utils.isDateValid(scanner);
//                    newAdmin.setDob(dobParsed);
//                    adminObj.addAdmin(newAdmin);
//                    break;
//                case 5:
//
//                            System.out.println("Please enter semesterId :");
//                            String semesterId = scanner.nextLine();
//                            System.out.println("Please enter course ID");
//                            String courseId = scanner.nextLine();
//                            System.out.println("Please enter course name");
//                            String courseName = scanner.nextLine();
//                            System.out.println("Please enter available seats");
//                            int seatsAvailable = scanner.nextInt();
//                    adminObj.addCourse(semesterId,courseId,courseName,seatsAvailable);
//                    break;
//                case 6:
//                    String id_to_be_deleted = scanner.nextLine();
//                    adminObj.removeCourse(id_to_be_deleted);
//
//                    break;
//                case 7:
//                    userService.viewCourseCatalogue(true);
//                    break;
//                case 8:
//                    userService.logOut(admin);
//                    System.out.println("Menu Exited");
//                    return;
//                default:
//                    System.out.println("Please select valid input");
//            }
//
//        }
//    }
//
//
//    /**
//     * Method which displays different options of admin menu
//     */
//    public void displayMenu() {
//
//        System.out.println();
//        System.out.println("**************************************************");
//        System.out.println();
//        System.out.println("Welcome to ADMIN portal, " + this.adminUsername);
//        System.out.println("You have logged in successfully at time " + new Date());
//        System.out.println("Press 1 to Approve student ");
//        System.out.println("Press 2 to Add professor");
//        System.out.println("Press 3 to Generate grade card");
//        System.out.println("Press 4 to Add admin");
//        System.out.println("Press 5 to Add course");
//        System.out.println("Press 6 to Remove course");
//        System.out.println("Press 7 to View Course Catalogue");
//        System.out.println("Press 8 to Logout");
//        System.out.println();
//        System.out.println("**************************************************");
//        System.out.println();
//    }
//}
