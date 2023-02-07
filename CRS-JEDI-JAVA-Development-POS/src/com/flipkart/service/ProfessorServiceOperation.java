package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.data.RegisteredCourseData;
import com.flipkart.data.UserData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProfessorServiceOperation extends UserServiceOperation implements ProfessorService{

    private Professor professor;
    private ProfessorDAO profDAO;

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
        System.out.println("Please select one of the following grades for each student: A,A-,B,B-,C,C-,D,D-,E,F");
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
//                   Grade gradeObj= new Grade();
//                    gradeObj.setGrade(gradeString);
//                   for(RegisteredCourse registration: registeredCourseList){
//                       if(registration.getStudentID().equals(student.getUserId()) &&
//                               registration.getSemesterID().equals(semesterId) &&
//                               registration.getCourseID().equals(courseToGrade.getCourseID())
//                       ){
//                           registration.setGrade(gradeObj);
//                            break;
//                       }
//                   }
                   } else {
                       System.out.println("Please enter one of the following grades: A" +
                               ",A-,B,B-,C,C-,D,D-,E,F");
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
        "B","B-","C","C-","D","D-","E","F"));
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

        //dao
        return profDAO.viewEnrolledStudentListDao(courseId,semesterId);
//        return this.getEnrolledStudentList(courseId,semesterId);
    }
//    public List<Student> getEnrolledStudentList (String courseId,String semesterId){
//        List<RegisteredCourse> regCourseList = RegisteredCourseData.regCourseList;
//        List<Student> enrolledStudentList = new ArrayList<Student>();
//        List<Student> studentList = UserData.studentList;
//
//        for(RegisteredCourse registeredCourse: regCourseList){
//            if(registeredCourse.getSemesterID().equals(semesterId) && registeredCourse.getCourseID().equals(courseId)){
//                String studentId = registeredCourse.getStudentID();
//                for(Student student: studentList) {
//                    if (student.getUserId().equals(studentId)) {
//                        enrolledStudentList.add(student);
//                        break;
//                    }
//                }
//
//            }
//        }
//        return enrolledStudentList;
//    }
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
