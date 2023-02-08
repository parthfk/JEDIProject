package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;

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
    public List<Student> viewEnrolledStudentList(String courseId,String semesterId){
        return profDAO.viewEnrolledStudentListDao(courseId,semesterId);
    }

    public void selectCourse(Course course){
//        List<Course> courseList = professor.getCoursesTaken();
//        courseList.add(course);
//        course.setProfessorID(professor.getUserId());
//        professor.setCoursesTaken(courseList);
        //dao
        profDAO.selectCourseDAO(course);
    }
    public List<Course> viewCourseList(){
        //this.printCourseList(courseList);
        return profDAO.viewCourseListDao(profId);
    }
}
