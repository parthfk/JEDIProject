package com.flipkart.service;

import com.flipkart.bean.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import com.flipkart.dao.*;
import com.flipkart.exception.*;
import com.flipkart.utils.Utils;

import java.util.List;

public class AdminServiceOperation extends UserServiceOperation implements AdminService {
//    private Scanner scanner;

    public AdminServiceOperation() {
//        scanner = new Scanner(System.in);
   }

    public boolean addCourse(String semesterId,String courseId,String courseName,int seatsAvailable) throws SQLException{
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

            Course newCourse = new Course(courseId, courseName, null, seatsAvailable);
            CatalogueDAO catalogueDAO = new CatalogueDAOImpl();
            CourseDAO courseDAO = new CourseDAOImpl();
            if (!courseDAO.doesCourseExist(courseId)) {
                courseDAO.addCourseToDB(newCourse);
            }
            catalogueDAO.addCourseInDB(newCourse,semesterId);



            System.out.println("Your new course added: " + newCourse);

        return true;
    }

    public boolean removeCourse(String id_to_be_deleted) throws CourseNotFoundException,SQLException{

     //   System.out.println("-----Below is the list of courses currently present-------");
        CatalogueDAOImpl catalogueDAO = new CatalogueDAOImpl();
    //    List<Course> courseList = catalogueDAO.fetchCatalogue(true);
//        boolean flag1=true;
//        StringBuffer buffer = new StringBuffer();
//        Formatter fmt = new Formatter();
//
//        for(Course c: courseList)
//        {
//            if(flag1){
//                fmt.format("\n%14s %14s\n", "Course Name", "Course ID");
//                flag1=false;
//            }
//            fmt.format("%14s %14s\n", c.getName(),c.getCourseID());
//        }
//        System.out.println(fmt);
//        buffer.setLength(0);


//        System.out.println("Enter the course ID to be deleted");
//       // String id_to_be_deleted = scanner.nextLine();
//        boolean flag = false;
//        for (Course c : courseList) {
//            if (c.getCourseID().equals(id_to_be_deleted)) {
                return catalogueDAO.deleteCourseInDB(id_to_be_deleted);
//                System.out.println("Course Deleted");
//                flag = true;
//                break;
//            }
//
//        }
//        if (!flag) {
//            System.out.println("No such Course exists !");
//            return false;
//        }
//
//        return true;
    }

    public boolean approveStudent(String studentId) throws SQLException {
        AdminDAOImpl obj = new AdminDAOImpl();
        return obj.approveStudentDAO(studentId);
    }


    public boolean addProfessor(Professor newProf) throws ProfessorAlreadyExistException,SQLException {
            AdminDAOImpl obj=new AdminDAOImpl();
            return obj.addProfessorDAO(newProf);

    }

    public boolean addAdmin(Admin newAdmin) throws AdminAlreadyExistException,AdminNotAddedException,SQLException {
        AdminDAOImpl obj = new AdminDAOImpl();
        boolean res = obj.addAdminDAO(newAdmin);
        if (res) {
            System.out.println("Admin Registered Successfully!!");
            return true;
        } else {
                throw new AdminNotAddedException(newAdmin.getEmail());
        }
    }

    public int generateGradeCard(String userId_of_approved_gradeCard) throws SQLException {
        AdminDAO adminDAO = new AdminDAOImpl();
        return adminDAO.generateGradeCardDAO(userId_of_approved_gradeCard);
    }

    @Override
    public List<List<String>> getUnApprovedStudents() throws SQLException {
        return new AdminDAOImpl().getListUnApprovedStudents();
    }
}
