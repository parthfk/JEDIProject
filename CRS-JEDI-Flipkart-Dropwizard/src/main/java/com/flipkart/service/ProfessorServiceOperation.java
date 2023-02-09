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

    Scanner in = new Scanner(System.in);
    public ProfessorServiceOperation(String professorId){
        this.profId = professorId;
        profDAO = new ProfessorDAOImpl(professorId);
    }

    public List<String> readCourseAndSemesterIds(){
        System.out.println("Please enter the course id ");
        String courseId = in.nextLine();
        System.out.println("Please enter the semesterId");
        String semesterId = in.nextLine();
        return Arrays.asList(courseId,semesterId);
    }
    public void printCourseList(List<Course> courseList){
        boolean flag =true;
        for(int i=0;i<courseList.size();i++){
            if(flag){
            System.out.println("Sr No. \t Course ID \tName");
            flag=true;
            }
            System.out.println((i+1)+"\t"+ courseList.get(i).getCourseID()+"\t\t" +courseList.get(i).getName());
        }
    }

    public boolean addGrade(String courseId, String semId, String studentId, String grade){
       try {
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
       }catch(Exception e){
           e.printStackTrace();
           e.getMessage();
           return false;
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
    public List<Student> viewEnrolledStudentList(String courseId,String semesterId){
        return profDAO.viewEnrolledStudentListDao(courseId,semesterId);
    }

    public void selectCourse(String courseId) throws SQLException, CourseNotFoundException {
//        List<Course> courseList = professor.getCoursesTaken();
//        courseList.add(course);
//        course.setProfessorID(professor.getUserId());
//        professor.setCoursesTaken(courseList);
        //dao
        List<Course> courses = new CatalogueDAOImpl().fetchCatalogue(false);
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
        //this.printCourseList(courseList);
        return profDAO.viewCourseListDao(profId);
    }
}
