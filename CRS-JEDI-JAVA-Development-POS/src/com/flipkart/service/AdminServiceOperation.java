package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.data.CourseData;
import com.flipkart.data.RegisteredCourseData;
import com.flipkart.data.UserData;

import java.util.ArrayList;
import java.util.Scanner;
import com.flipkart.dao.*;
import java.util.List;
public class AdminServiceOperation extends UserServiceOperation implements AdminService {
    private Scanner scanner;

    public AdminServiceOperation() {
        scanner = new Scanner(System.in);
    }

    public boolean addCourse() {
        System.out.println("Please enter semesterId :");
        String semesterId = scanner.nextLine();

        System.out.println("Please enter course ID");
        String courseId = scanner.nextLine();

        System.out.println("Please enter course name");
        String courseName = scanner.nextLine();

        System.out.println("Please enter available seats");
        int seatsAvailable = scanner.nextInt();

        try {
            Course newCourse = new Course(courseId, courseName,null, seatsAvailable);
            CatalogueDAO catalogueDAO = new CatalogueDAOImpl();
            CourseDAO courseDAO = new CourseDAOImpl();
            if(!courseDAO.doesCourseExist(courseId)){
                courseDAO.addCourseToDB(newCourse);
            }
            catalogueDAO.addCourseInDB(newCourse,semesterId);


            System.out.println("Your new course added: " + newCourse);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean removeCourse() {
        System.out.println("-----Below is the list of courses currently present-------");
        CatalogueDAOImpl catalogueDAO = new CatalogueDAOImpl();
        List<Course> courseList = catalogueDAO.fetchCatalogue();
        for (Course c :courseList) {
            System.out.println("Course Name : " + c.getName() + "  Course ID : " + c.getCourseID());
        }
        System.out.println("Enter the course ID to be deleted");
        String id_to_be_deleted = scanner.next();
        boolean flag = false;
        for (Course c :courseList) {
            if (c.getCourseID().equals(id_to_be_deleted)) {
                catalogueDAO.deleteCourseInDB(id_to_be_deleted);
                System.out.println("Course Deleted");
                flag = true;
                break;
            }

        }
        if (!flag) {
            System.out.println("No such Course exists !");
            return false;
        }

        return true;
    }

    public void approveStudent() {

        AdminDAOImpl obj = new AdminDAOImpl();
        obj.approveStudentDAO();

//        for (Student s : UserData.studentList) {
//            if (!s.isStatusApproval())
//                System.out.println("Student User ID: " + s.getUserId() + " Student Name: " + s.getName() + " Student Department: " + s.getDepartmentID() + " Student Email: " + s.getEmail());
//        }
//        while (true) {
//            System.out.println("Enter student UserID or Press # to exit");
//            String user_ID = scanner.next();
//            if (user_ID.equals("#"))
//                return;
//            for (Student s : UserData.studentList) {
//                if (s.getUserId().equals(user_ID)) {
//                    s.setStatusApproval(true);
//                    System.out.println("Student Approved");
//                    break;
//                }
//            }
//        }
    }


    public void addProfessor() {
        Professor newProf = new Professor();
        System.out.println("Enter new Professor ID");
        newProf.setUserId(scanner.next());
        System.out.println("Enter new Professor Password");
        newProf.setPassword(scanner.next());
        System.out.println("Enter new Professor Name");
        newProf.setName(scanner.next());
        System.out.println("Enter new Professor Email");
        newProf.setEmail(scanner.next());
        System.out.println("Enter new Professor DepartmentID");
        newProf.setDepartmentID(scanner.next());
        newProf.setUserType("professor");

        try {
            AdminDAOImpl obj=new AdminDAOImpl();
            obj.addProfessorDAO(newProf);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    public boolean addAdmin() {
        Admin newAdmin = new Admin();
        System.out.println("Enter new Admin ID");
        newAdmin.setUserId(scanner.next());

        System.out.println("Enter new Admin Password");
        newAdmin.setPassword(scanner.next());
        System.out.println("Enter new Admin Name");
        newAdmin.setName(scanner.next());
        System.out.println("Enter new Admin Email");
        newAdmin.setEmail(scanner.next());
        newAdmin.setUserType("admin");

        try {
            AdminDAOImpl obj=new AdminDAOImpl();
            obj.addAdminDAO(newAdmin);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //functionality remaining
    public void generateGradeCard() {
        while (true) {
            for (Student s : UserData.studentList) {
                if (s.isStatusApproval() && !s.isGradeCardApproved())
                    System.out.println("Name of student : " + s.getName() + " UserID : " + s.getUserId() + " Email " + s.getEmail() + " Department Registered in " + s.getDepartmentID());
            }
            System.out.println("Enter UserID of student to approve Grade Gard or Press # to exit");
            String userId_of_approved_gradeCard = scanner.next();
            if (userId_of_approved_gradeCard.equals("#")) {
                return;
            }
            boolean userFound = false;
            Boolean gradeNotAssigned = false;
            for (Student s : UserData.studentList) {
                if (s.getUserId().equals(userId_of_approved_gradeCard)) {
                    GradeCard gradeCard = new GradeCard();
                    gradeCard.setStudentID(userId_of_approved_gradeCard);

                    float gradeTotal = 0;
                    int num = 0;
                    ArrayList<String> courseList = new ArrayList<>();
                    ArrayList<Grade> gradeList = new ArrayList<>();
                    String semID = "0";

                    for (RegisteredCourse c : RegisteredCourseData.regCourseList) {
                        if (c.getStudentID().matches(userId_of_approved_gradeCard)) {
                            Grade grade = c.getGrade();
                            if (grade == null) {
                                gradeNotAssigned = true;
                                break;
                            }
                            semID = c.getSemesterID();
                            // continue;
                            String gradeSymbol = grade.getGrade();
                            if (gradeSymbol.matches("A+")) {
                                gradeTotal += 10;
                            } else if (gradeSymbol.matches("A-")) {
                                gradeTotal += 9;
                            } else if (gradeSymbol.matches("B+")) {
                                gradeTotal += 8;
                            } else if (gradeSymbol.matches("B-")) {
                                gradeTotal += 7;
                            } else if (gradeSymbol.matches("C+")) {
                                gradeTotal += 6;
                            } else if (gradeSymbol.matches("C-")) {
                                gradeTotal += 5;
                            } else if (gradeSymbol.matches("D+")) {
                                gradeTotal += 4;
                            }

                            num += 10;
                            courseList.add(c.getCourseID());
                            gradeList.add(c.getGrade());
                        }
                    }
                    if (gradeNotAssigned) {
                        System.out.println("Cannot generate Grade Card, few courses are yet to be assigned grades for this student ");
                        break;
                    }
                    gradeCard.setCourseList(courseList);
                    gradeCard.setGrades(gradeList);
                    gradeCard.setSGPA(gradeTotal / num);
                    gradeCard.setSemesterID(semID);
                    s.setGradeCard(gradeCard);
                    s.setGradeCardApproved(true);
                    System.out.println("Grade Card Generated");
                    userFound = true;
                    break;
                }
            }
            if (!userFound && !gradeNotAssigned) {
                System.out.println("Not a valid ID, Try again!");
            }
        }
    }
}
