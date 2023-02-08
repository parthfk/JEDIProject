package com.flipkart.service;
import java.util.Formatter;

import com.flipkart.bean.*;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProfessorServiceOperation extends UserServiceOperation implements ProfessorService{
    private Professor professor;
    private ProfessorDAO profDAO;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    Scanner in = new Scanner(System.in);
    public ProfessorServiceOperation(Professor professor){
        this.professor =professor;
        profDAO = new ProfessorDAOImpl(professor);
    }

    public List<String> readCourseAndSemesterIds(){
        System.out.println("Please enter the course id ");
        String courseId = in.nextLine();
        System.out.println("Please enter the semesterId");
        String semesterId = in.nextLine();
        return Arrays.asList(courseId,semesterId);
    }
    public void printCourseList(List<Course> courseList) {
        boolean flag = true;

        Formatter fmt = new Formatter();
        fmt.format("\n%-17s %-17s %-17s\n", ANSI_CYAN+"S.no"+ANSI_RESET, ANSI_CYAN+"Course Id"+ANSI_RESET, ANSI_CYAN+"Name"+ANSI_RESET);
        for (int i = 0; i < courseList.size(); i++) {
            if (flag) {
                fmt.format("%-17s %-17s %-17s\n", ANSI_RESET+i + 1+ANSI_RESET, ANSI_RESET+courseList.get(i).getCourseID()+ANSI_RESET, ANSI_RESET+courseList.get(i).getName()+ANSI_RESET);
                flag = true;
            }
        }
        System.out.println(fmt);
    }

    public void addGrade(){
       List<Course> coursesList = this.viewCourseList();
       Course courseToGrade = null;
       System.out.println("Please enter the semesterId");
       String semesterId = in.nextLine();
       System.out.println("Please select one of the following courses to grade: ");
       this.printCourseList(coursesList);

       System.out.println("Please select Serial number to add course grade out of " +coursesList.size());
       int courseIdx = -1;
       try {
            courseIdx = Integer.parseInt(in.nextLine());
            courseToGrade = coursesList.get(courseIdx-1);
       }
       catch(Exception e){
           e.printStackTrace();
       }
       System.out.println("Please select one of the following grades for each student: A,A-,B,B-,C,C-,D,F");
       try {
           List<Student> enrolledStudents = profDAO.viewEnrolledStudentListDao(courseToGrade.getCourseID(), semesterId);

           for (Student student : enrolledStudents) {
               boolean gradeValidated = false;
               while (!gradeValidated) {
                   System.out.println("Enter grade for studentID: " + student.getUserId());
                   String gradeString = in.nextLine();
                   gradeValidated = this.validateGrade(gradeString);
                   if (gradeValidated) {
                       profDAO.addGrade(student.getUserId(), semesterId, courseToGrade.getCourseID(), gradeString);
                   } else {
                       System.out.println("Please enter one of the following grades: A" +
                               ",A-,B,B-,C,C-,D,F");
                   }
               }
           }
           System.out.println("Grading for courseID: " + courseToGrade.getCourseID() + " done successfully !");
       }catch(Exception e){
           e.printStackTrace();
       }
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
    public List<Student> viewEnrolledStudentList(){
        List<String> courseAndSemIds = this.readCourseAndSemesterIds();
        String courseId = courseAndSemIds.get(0);
        String semesterId = courseAndSemIds.get(1);

        return profDAO.viewEnrolledStudentListDao(courseId,semesterId);
    }
    public void selectCourse(Course course){
        List<Course> courseList = professor.getCoursesTaken();
        courseList.add(course);
        course.setProfessorID(professor.getUserId());
        professor.setCoursesTaken(courseList);
        //dao
        profDAO.selectCourseDAO(course);
    }
    public List<Course> viewCourseList(){
        List<Course> courseList = profDAO.viewCourseListDao(professor.getUserId());
        this.printCourseList(courseList);
        return courseList;
    }
}
