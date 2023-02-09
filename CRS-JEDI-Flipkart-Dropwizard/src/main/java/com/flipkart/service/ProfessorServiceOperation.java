package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.CatalogueDAOImpl;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotValidException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProfessorServiceOperation extends UserServiceOperation implements ProfessorService{
    private String profId;
    private ProfessorDAO profDAO;

    public ProfessorServiceOperation(String professorId){
        this.profId = professorId;
        profDAO = new ProfessorDAOImpl(professorId);
    }

    public boolean addGrade(String courseId, String semId, String studentId, String grade) throws GradeNotValidException{
           boolean gradeValidated = false;
           while (!gradeValidated) {
               gradeValidated = this.validateGrade(grade);
               if (gradeValidated) {
                   profDAO.addGrade(studentId, semId, courseId, grade);
                   return true;
               } else {
                   throw new GradeNotValidException(grade);
               }
           }

        return false;
    }
    public boolean validateGrade(String gradeEntered){
        List<String> possibleGrades = new ArrayList<>(Arrays.asList("A","A-",
        "B","B-","C","C-","D","F"));
        for(String possibleGrade: possibleGrades){
            if(possibleGrade.equals(gradeEntered)){
                return true;
            }
        }
        return false;
    }
    public List<Student> viewEnrolledStudentList(String courseId,String semesterId) throws SQLException, CourseNotFoundException {

        List<Course> courses = new CatalogueDAOImpl().fetchCatalogue(true);
        boolean  isFound = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseID().matches(courseId)) {
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            throw new CourseNotFoundException(courseId);
        }

        return profDAO.viewEnrolledStudentListDao(courseId,semesterId);
    }

    public void selectCourse(String courseId) throws SQLException, CourseNotFoundException {

        List<Course> courses = new CatalogueDAOImpl().fetchCatalogue(true);
        boolean  isFound = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseID().matches(courseId)) {
                isFound = true;
                break;
            }
        }

        if (!isFound) {
                throw new CourseNotFoundException(courseId);
        }

        profDAO.selectCourseDAO(courseId);
    }
    public List<Course> viewCourseList(){
        return profDAO.viewCourseListDao(profId);
    }

    @Override
    public boolean logOut(User user) {
        return false;
    }
}
