package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.SemRegistration;
import com.flipkart.bean.Student;
import com.flipkart.data.CourseData;
import com.flipkart.data.UserData;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class StudentServiceOperation extends UserServiceOperation implements StudentService  {

    private Student student;
    public StudentServiceOperation(Student student) {
        this.student = student;
    }

    public void registerForSem() {
        student.setSemRegistration(new SemRegistration());
        System.out.println("Press 1 to view course catalog");
        System.out.println("Press 2 to add Primary Courses");
        System.out.println("Press 3 to add Secondary Courses");
        System.out.println("Press 4 to confirm and proceed with final registration");
        System.out.println("Press 5 to delete a Courses");
        System.out.println("Press 6 to pay fee");
        System.out.println("Press 7 to go back to student menu");

        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        switch(input) {
            case 1:
                super.viewCourseCatalogue();
                break;
            case 2:
                selectPrimaryCourse();
                break;
            case 3:
                selectSecondaryCourse();
                break;
            case 4:
                confirmRegistration();
                break;
            case 5:
                dropCourse();
                break;
            case 6:
                payFee();
                return;
            case 7:
                return;
            default:
                break;
        }
    }

    public void signup() {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter your name");
        String name=in.nextLine();
        System.out.println("Enter your Email Id");
        String emailEntered=in.nextLine();
        System.out.println("Enter your password");
        String password=in.nextLine();
        System.out.println("Enter your Department Id");
        String departmentId=in.nextLine();
        Student newStudent=new Student(name,password,emailEntered,departmentId);
        UserData.studentList.add(newStudent);
    }
    public void selectPrimaryCourse() {
        System.out.println("Enter 4 Course Id's to be added as your primary course");
        Scanner in=new Scanner(System.in);
        ArrayList<Course> primaryCourses=new ArrayList<Course>();
        Course course1=getCourseFromId(in.nextLine());
        Course course2=getCourseFromId(in.nextLine());
        Course course3=getCourseFromId(in.nextLine());
        Course course4=getCourseFromId(in.nextLine());

        primaryCourses.add(course1);
        primaryCourses.add(course2);
        primaryCourses.add(course3);
        primaryCourses.add(course4);
        student.getSemRegistration().setPrimaryCourses(primaryCourses);
        System.out.println("Primary courses added successfully");
    }

    private Course getCourseFromId(String courseId) {
        for (Course c: CourseData.courseList) {
            if(c.getCourseID().matches(courseId)) {
                return c;
            }
        }
        return null;
    }

    public void selectSecondaryCourse() {
        System.out.println("Enter 2 Course Id's to be added as your secondary course");
        Scanner in=new Scanner(System.in);
        ArrayList<Course> secondaryCourses=new ArrayList<Course>();
        Course course1=getCourseFromId(in.nextLine());
        Course course2=getCourseFromId(in.nextLine());

        secondaryCourses.add(course1);
        secondaryCourses.add(course2);
        student.getSemRegistration().setPrimaryCourses(secondaryCourses);
        System.out.println("Secondary courses added successfully");
    }

    public void confirmRegistration() {
        // Proceed with business logic for course verification.
    }

    public void addCourse() {
        Scanner in = new Scanner(System.in);

        if(student.getCourseRegistered().size()>=4)
        {
            System.out.println("Course limit reached, please drop a course to add another");
            return;
        }

        System.out.println("Enter course Id to add : ");
        String courseToAdd = in.nextLine();

        List<Course> courses = CourseData.courseList;
        for(int i=0;i<courses.size();i++){
            if(courses.get(i).getCourseID().matches(courseToAdd) && courses.get(i).getAvailableSeats()>0 )
            {
                List<Course> courselist=student.getCourseRegistered();
                courselist.add(courses.get(i));
                courses.get(i).setAvailableSeats(courses.get(i).getAvailableSeats()-1);
                student.setCourseRegistered(courselist);
                System.out.println("Course added successfully ");

                return;
            }
        }
    }

    public void dropCourse() {
        System.out.println("Enter course Id to drop : ");
        Scanner in = new Scanner(System.in);
        String courseId= in.nextLine();

        Course toBeDropped = null;
        for  (Course c: student.getCourseRegistered()) {
            if (c.getCourseID().matches(courseId)) {
                toBeDropped = c;
                break;
            }
        }
        if (toBeDropped != null) {
            student.getCourseRegistered().remove(toBeDropped);
            System.out.println("Course dropped successfully");
        }
        else {
            System.out.println("No such registered course exists");

        }
    }
    
    public void payFee() {

    }

    public GradeCard displayGradeCard() {
        return null;
    }
}
