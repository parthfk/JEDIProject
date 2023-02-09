//package com.flipkart.client;
//
//import com.flipkart.bean.Course;
//import com.flipkart.bean.Professor;
//import com.flipkart.bean.Student;
//import com.flipkart.dao.CatalogueDAOImpl;
////import com.flipkart.data.CourseData;
//import com.flipkart.exception.CourseNotAvailableException;
//import com.flipkart.exception.CourseNotFoundException;
//import com.flipkart.service.ProfessorService;
//import com.flipkart.service.ProfessorServiceOperation;
//import com.flipkart.service.UserService;
//import com.flipkart.service.UserServiceOperation;
//
//import java.util.Formatter;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Date;
//
///**
// * Contains the functionality to display Professor Menu
// */
//public class CRSProfessorMenu {
//    // 1. select courses to teach
//    // 2. view course catalog
//    // 3. view enrolled students
//    // 4. add grade
//    // 5. logout
//    private String professorUsername;
//    private Scanner scanner;
//    private UserService userService;
//
//    /**
//     * constructor
//     * @param professor
//     */
//    public CRSProfessorMenu(Professor professor) {
//        this.professorUsername = professor.getName();
//        scanner = new Scanner(System.in);
//        userService = new UserServiceOperation();
//        displayMenu(professor);
//    }
//
//    /**
//     * Method which implements different functions according to the option chosen by the professor
//     * @param professor
//     */
//    public void displayMenu(Professor professor) {
//
//        while(true) {
//            consolePrints();
//            int input = -1;
//            try {
//                input = Integer.parseInt(scanner.nextLine());
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
//            ProfessorService profService = new ProfessorServiceOperation(professor);
//
//            switch (input) {
//                case 1:
//                    List<Course> courseList = new CatalogueDAOImpl().fetchCatalogue(true);
//                    if(courseList.size()==0)
//                        System.out.println("No courses available");
//
//                    System.out.println("These are the courses currently available: ");
//
////                    System.out.println("Course ID \tCourse Name");
////                    for(int i=0;i<courseList.size();i++){
////                        System.out.println(courseList.get(i).getCourseID()+"\t \t \t " +courseList.get(i).getName());
////                    }
//
//                    StringBuffer buffer = new StringBuffer();
//                    Formatter fmt = new Formatter();
//
//                    fmt.format("\n%14s %14s\n", "Course ID", "Course Name");
//
//                    for(Course c: courseList)
//                    {
//                        fmt.format("%14s %14s\n", c.getCourseID(),c.getName());
//                    }
//                    System.out.println(fmt);
//                    buffer.setLength(0);
//
//
//                    while(true){
//                        System.out.println("Please enter the courseId to teach or # to exit.");
//                        String inputCourseId = scanner.nextLine();
//                        if(inputCourseId.equals("#")){
//                            break;
//                        }
//                        else {
//                            boolean isA = false;
//
//                            for (Course c : courseList) {
//                                if (c.getCourseID().equals((inputCourseId))) {
//                                    profService.selectCourse(c);
//                                    isA = true;
//                                }
//                            }
//                            if(!isA)
//                            {
//                                try {
//                                    throw new CourseNotFoundException(inputCourseId);
//                                } catch (CourseNotFoundException e) {
//                                    System.out.println(e.getMessage());
//                                }
//                            }
//                        }
//                    }
//                    break;
//                        case 2:
//                    profService.viewCourseList();
//
//                    break;
//                case 3:
//                    List<Student> studentList = profService.viewEnrolledStudentList();
//                    if(studentList != null){
////                           System.out.println("Name \t \t ID");
////                            for(Student s : studentList){
////                            System.out.println(s.getName() +"\t"+ s.getUserId());
//                        buffer = new StringBuffer();
//                         fmt = new Formatter();
//
//                        fmt.format("\n%14s %14s\n", "Name", "ID");
//
//                        for(Student s: studentList)
//                        {
//                            fmt.format("%14s %14s\n",s.getName(),s.getUserId());
//                        }
//                        System.out.println(fmt);
//                        buffer.setLength(0);
//                    }
//                    break;
//                case 4:
//                    profService.addGrade();
//                    break;
//                case 5:
//                    userService.logOut(professor);
//                    System.out.println("Menu Exited");
//                    return;
//                default:
//                    System.out.println("Please select valid input");
//            }
//        }
//    }
//
//    /**
//     * Methods which displays different option in the professor menu
//     */
//    private void consolePrints() {
//        System.out.println();
//        System.out.println("**************************************************");
//        System.out.println();
//        System.out.println("Welcome to PROFESSOR portal, " + this.professorUsername);
//        System.out.println("You have logged in successfully at time " + new Date());
//        System.out.println("Press 1 to Select courses to teach");
//        System.out.println("Press 2 to View selected courses");
//        System.out.println("Press 3 to View enrolled students");
//        System.out.println("Press 4 to Add grades");
//        System.out.println("Press 5 to Logout");
//        System.out.println();
//        System.out.println("**************************************************");
//        System.out.println();
//    }
//}
