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

    public AdminServiceOperation() {
   }

    public boolean addCourse(String semesterId,String courseId,String courseName,int seatsAvailable) throws SQLException{


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

       CatalogueDAOImpl catalogueDAO = new CatalogueDAOImpl();
                return catalogueDAO.deleteCourseInDB(id_to_be_deleted);
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

    @Override
    public boolean logOut(User user) {
        return true;
    }
}
