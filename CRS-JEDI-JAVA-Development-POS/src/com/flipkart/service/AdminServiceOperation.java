package com.flipkart.service;

import com.flipkart.bean.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import com.flipkart.dao.*;
import com.flipkart.exception.AdminNotAddedException;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.utils.Utils;

import java.util.List;

public class AdminServiceOperation extends UserServiceOperation implements AdminService {
    private Scanner scanner;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

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
            Course newCourse = new Course(courseId, courseName, null, seatsAvailable);
            CatalogueDAO catalogueDAO = new CatalogueDAOImpl();
            CourseDAO courseDAO = new CourseDAOImpl();
            if (!courseDAO.doesCourseExist(courseId)) {
                courseDAO.addCourseToDB(newCourse);
            }
            else {
                throw new CourseAlreadyRegisteredException(courseId);
            }
            catalogueDAO.addCourseInDB(newCourse,semesterId);



            System.out.println("Your new course added: " + newCourse);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean removeCourse() {

        System.out.println("-----Below is the list of courses currently present-------");
        CatalogueDAOImpl catalogueDAO = new CatalogueDAOImpl();
        List<Course> courseList = catalogueDAO.fetchCatalogue(true);
        boolean flag1=true;
        StringBuffer buffer = new StringBuffer();
        Formatter fmt = new Formatter();

        for(Course c: courseList)
        {
            if(flag1){
                fmt.format("\n%17s %17s\n", ANSI_CYAN+"Course Name"+ANSI_RESET, ANSI_CYAN+"Course ID"+ANSI_RESET);
                flag1=false;
            }
            fmt.format("%17s %17s\n", ANSI_CYAN+c.getName()+ANSI_RESET,ANSI_CYAN+c.getCourseID()+ANSI_RESET);
        }
        System.out.println(fmt);
        buffer.setLength(0);


        System.out.println("Enter the course ID to be deleted");
        String id_to_be_deleted = scanner.nextLine();
        boolean flag = false;
        for (Course c : courseList) {
            if (c.getCourseID().equals(id_to_be_deleted)) {
                catalogueDAO.deleteCourseInDB(id_to_be_deleted);
                System.out.println("Course Deleted");
                flag = true;
                break;
            }

        }
        if (!flag) {
            System.out.println(ANSI_YELLOW+
                    "No such Course exists !"+
                    ANSI_RESET);
            return false;
        }

        return true;
    }

    public void approveStudent() {
        AdminDAOImpl obj = new AdminDAOImpl();
        obj.approveStudentDAO();
    }


    public void addProfessor() {
        Professor newProf = new Professor();

        System.out.println("Enter new Professor Name");
        newProf.setName(scanner.next());
        System.out.println("Enter new Professor Email");
        newProf.setEmail(scanner.next());
        System.out.println("Enter new Professor Password");
        newProf.setPassword(scanner.next());
        System.out.println("Enter new Professor DepartmentID");
        newProf.setDepartmentID(scanner.next());
        newProf.setUserType("professor");
        System.out.println("Enter professor's address");
        String address = scanner.next();
        newProf.setAddress(address);
        System.out.println("Enter new professor's mobile number");
        String mobileNumber = scanner.next();
        while (!Utils.isPhoneNumberValid(mobileNumber)) {
            System.out.println(ANSI_YELLOW+
                    "Your mobile number is invalid. It must a 10 digit numeric. Please enter again"+
                    ANSI_RESET);
            mobileNumber = scanner.nextLine();
        }

        newProf.setMobileNumber(mobileNumber);
        Date dobParsed = Utils.isDateValid(scanner);
        newProf.setDob(dobParsed);
        try {

            AdminDAOImpl obj=new AdminDAOImpl();
            if(!obj.addProfessorDAO(newProf))
            {
                throw new ProfessorNotAddedException(newProf.getEmail());
            }

        } catch (Exception e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

    public boolean addAdmin() {
        Admin newAdmin = new Admin();

        System.out.println("Enter new Admin Name");
        newAdmin.setName(scanner.next());
        System.out.println("Enter new Admin Email");
        newAdmin.setEmail(scanner.next());
        newAdmin.setUserType("admin");
        System.out.println("Enter new Admin Password");
        newAdmin.setPassword(scanner.next());
        System.out.println("Enter new admin's address");
        String address = scanner.next();
        newAdmin.setAddress(address);
        System.out.println("Enter new admin's number");
        String mobileNumber = scanner.next();
        while (!Utils.isPhoneNumberValid(mobileNumber)) {
            System.out.println(ANSI_YELLOW+
                    "Your mobile number is invalid. It must a 10 digit numeric. Please enter again"+
                    ANSI_RESET);
            mobileNumber = scanner.nextLine();
        }
        newAdmin.setMobileNumber(mobileNumber);
        Date dobParsed = Utils.isDateValid(scanner);
        newAdmin.setDob(dobParsed);

        AdminDAOImpl obj = new AdminDAOImpl();
        boolean res = obj.addAdminDAO(newAdmin);
        if (res) {
            System.out.println("Admin Registered Successfully!!");
            return true;
        } else {
            try {
                throw new AdminNotAddedException(newAdmin.getEmail());
            } catch (AdminNotAddedException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public void generateGradeCard() {
        AdminDAO adminDAO = new AdminDAOImpl();
        adminDAO.generateGradeCardDAO();
    }
}
