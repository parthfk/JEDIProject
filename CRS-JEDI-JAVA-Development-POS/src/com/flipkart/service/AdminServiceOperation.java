package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.data.CourseData;
import com.flipkart.data.UserData;

import java.util.Scanner;

public class AdminServiceOperation extends UserServiceOperation implements AdminService{
    private Scanner scanner;
    CourseData courseData;

    public AdminServiceOperation() {
        scanner = new Scanner(System.in);
        courseData = new CourseData();
    }

    public boolean addCourse() {
        System.out.println("Please enter course ID");
        String courseId = scanner.nextLine();

        System.out.println("Please enter course name");
        String courseName = scanner.nextLine();

        System.out.println("Please enter professor assigned");
        String professorAssigned = scanner.nextLine();

        System.out.println("Please enter available seats");
        int seatsAvailable = scanner.nextInt();

        try {
            Course newCourse = new Course(courseId, courseName, professorAssigned, seatsAvailable);
            CourseData.courseList.add(newCourse);
            System.out.println("Your new course added: " + newCourse.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean removeCourse(String courseId)  {
        System.out.println("Please enter course ID");
        String courseIdToBeRemoved = scanner.nextLine();

        try {
            CourseData.removeCourse(courseId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void approveStudent(Student student) {
        System.out.println("The profile of student is : \n"+
                "Student Name: "+student.getName() + "\n" +
                "Student Email: " + student.getEmail() + "\n" +
                "Student Department: " + student.getDepartmentID());

        System.out.println("1. Approve \n"+
                           "2. Disapprove");

        int opt = scanner.nextInt();

        if(opt == 1) {
            student.setStatusApproval(true);
            UserData.userList.add(student);
        }else {
            student.setStatusApproval(false);
        }


        System.out.println("Student status updated !!!");
    }


    public boolean addProfessor(Professor professor) {
        professor.setUserType("Professor");
        try {
            UserData.userList.add(professor);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean addAdmin(Admin admin) {
        admin.setUserType("Admin");
        try{
            UserData.userList.add(admin);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void approveGradeCard(Student student) {
        System.out.println("1. Approve \n"+
                "2. Disapprove");

        int opt = scanner.nextInt();
        student.setGradeCardApproved(opt == 1);

        System.out.println("Grade card approval status updated!!");
    }
}
