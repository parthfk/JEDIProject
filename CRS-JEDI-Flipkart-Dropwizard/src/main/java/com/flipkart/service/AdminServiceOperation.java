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
//    private Scanner scanner;

    public AdminServiceOperation() {
//        scanner = new Scanner(System.in);
   }

    public boolean addCourse(String semesterId,String courseId,String courseName,int seatsAvailable) {
//        System.out.println("Please enter semesterId :");
//        String semesterId = scanner.nextLine();
//
//        System.out.println("Please enter course ID");
//        String courseId = scanner.nextLine();
//
//        System.out.println("Please enter course name");
//        String courseName = scanner.nextLine();
//
//        System.out.println("Please enter available seats");
//        int seatsAvailable = scanner.nextInt();

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

    public boolean removeCourse(String id_to_be_deleted) {

        System.out.println("-----Below is the list of courses currently present-------");
        CatalogueDAOImpl catalogueDAO = new CatalogueDAOImpl();
        List<Course> courseList = catalogueDAO.fetchCatalogue(true);
        boolean flag1=true;
        StringBuffer buffer = new StringBuffer();
        Formatter fmt = new Formatter();

        for(Course c: courseList)
        {
            if(flag1){
                fmt.format("\n%14s %14s\n", "Course Name", "Course ID");
                flag1=false;
            }
            fmt.format("%14s %14s\n", c.getName(),c.getCourseID());
        }
        System.out.println(fmt);
        buffer.setLength(0);


        System.out.println("Enter the course ID to be deleted");
       // String id_to_be_deleted = scanner.nextLine();
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
            System.out.println("No such Course exists !");
            return false;
        }

        return true;
    }

    public void approveStudent(String studentId) {
        AdminDAOImpl obj = new AdminDAOImpl();
        obj.approveStudentDAO(studentId);
    }


    public void addProfessor(Professor newProf) {
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

    public void generateGradeCard(String userId_of_approved_gradeCard) {
        AdminDAO adminDAO = new AdminDAOImpl();
        adminDAO.generateGradeCardDAO(userId_of_approved_gradeCard);
    }
}
