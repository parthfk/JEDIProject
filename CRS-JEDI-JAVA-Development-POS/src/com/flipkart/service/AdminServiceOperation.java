package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.data.CourseData;
import com.flipkart.data.UserData;

import java.util.Scanner;

public class AdminServiceOperation extends UserServiceOperation implements AdminService{
    private Scanner scanner;

    public AdminServiceOperation() {
        scanner = new Scanner(System.in);
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
            System.out.println("Your new course added: " + newCourse);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean removeCourse()  {
        System.out.println("-----Below is the list of courses currently present-------");
        for(Course c : CourseData.courseList)
        {
            System.out.println("Course Name : "+c.getName()+"  Course ID : "+c.getCourseID());
        }
        System.out.println("Enter the course ID to be deleted");
        String  id_to_be_deleted=scanner.next();
        boolean flag = false;
        for(Course c : CourseData.courseList)
        {
            if(c.getCourseID().equals(id_to_be_deleted))
            {
                CourseData.courseList.remove(c);
                System.out.println("Course Deleted");
                flag=true;
                break;
            }

        }
        if(!flag)
        {
            System.out.println("No such Course Exist");
            return false;
        }

        return true;
    }

    public void approveStudent() {

        for(Student s:UserData.studentList)
        {
            if(!s.isStatusApproval())
                System.out.println("Student User ID: "+s.getUserId()+"Student Name: "+s.getName()+"Student Department: "+s.getDepartmentID()+"Student Email: "+s.getEmail());
        }
        while(true)
        {
            System.out.println("Enter student UserID or Press # to exit");
            String user_ID=scanner.next();
            if(user_ID.equals("#"))
                return;
            for(Student s:UserData.studentList)
            {
                if(s.getUserId().equals(user_ID))
                {
                    s.setStatusApproval(true);
                    System.out.println("Student Approved");
                    break;
                }
            }
        }
    }


    public void addProfessor() {


        Professor newProf = new Professor();
        System.out.println("Enter new Professor ID");
        newProf.setUserId(scanner.next());
        System.out.println("Enter new Professor Password");
        newProf.setPassword(scanner.next());
        System.out.println("Enter new Professor Name");
        newProf.setName(scanner.next());
        System.out.println("Enter new Professor Email");
        newProf.setEmail(scanner.next());
        System.out.println("Enter new Professor DepartmentID");
        newProf.setDepartmentID(scanner.next());

        newProf.setUserType("professor");


        try {
            UserData.professorList.add(newProf);
            System.out.println("New professor added!");
        } catch (Exception ignored) {
        }
    }

    public boolean addAdmin() {
        Admin newAdmin = new Admin();
        System.out.println("Enter new Admin ID");
        newAdmin.setUserId(scanner.next());

        System.out.println("Enter new Admin Password");
        newAdmin.setPassword(scanner.next());
        System.out.println("Enter new Admin Name");
        newAdmin.setName(scanner.next());
        System.out.println("Enter new Admin Email");
        newAdmin.setEmail(scanner.next());
        newAdmin.setUserType("admin");

        try{
            UserData.adminList.add(newAdmin);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    //functionality remaining
    public void generateGradeCard() {
        while(true) {
            for(Student s: UserData.studentList)
            {
                if(s.isStatusApproval() && !s.isGradeCardApproved())
                    System.out.println("Name of student : "+ s.getName()+"UserID : "+ s.getUserId()+"Email "+s.getEmail()+"Department Registered in "+s.getDepartmentID());
            }
            System.out.println("Enter UserID of student to approve Grade Gard or Press # to exit");
            String userId_of_approved_gradeCard = scanner.next();
            if(userId_of_approved_gradeCard.equals("#"))
            {
                return;
            }
            boolean userFound = false;
            for(Student s: UserData.studentList)
            {
                if(s.getUserId().equals(userId_of_approved_gradeCard))
                {
                    s.setGradeCardApproved(true);
                    System.out.println("Grade Card Generated");
                    userFound = true;
                    break;
                }
            }
            if(!userFound){
                System.out.println("Not a valid ID, Try again!");
            }
        }
    }
}
