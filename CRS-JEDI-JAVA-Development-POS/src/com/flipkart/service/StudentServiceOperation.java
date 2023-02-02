package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.constant.PaymentMode;
import com.flipkart.data.CourseData;
import com.flipkart.data.UserData;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class StudentServiceOperation extends UserServiceOperation implements StudentService  {

    private Student student;
    public StudentServiceOperation() {}
    public StudentServiceOperation(Student student) {
        this.student = student;
    }

    public void registerForSem() {
        student.setSemRegistration(new SemRegistration(student));

        boolean registering = true;
        while(registering) {
            System.out.println("Press 1 to view course catalog");
            System.out.println("Press 2 to add Primary Courses");
            System.out.println("Press 3 to add Secondary Courses");
            System.out.println("Press 4 to confirm and proceed with final registration");
            System.out.println("Press 5 to pay fee");

            boolean addDropWindow = true; // todo change
            if (addDropWindow) {
                System.out.println("Press 6 to add a course ");
                System.out.println("Press 7 to delete a Courses");
            }
            System.out.println("Press # to go back to student menu");

            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
            switch (input) {
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
                    registering = false;
                    return;
                default:
                    break;
            }
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
        Student newStudent=new Student(name,emailEntered,password,departmentId);
        newStudent.setUserId(Integer.toString(new Random().nextInt(100)));
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
        List<Course> coursesRegistered = new ArrayList<>();
        for (Course c: student.getSemRegistration().getPrimaryCourses()) {
            if (c.getAvailableSeats() <= 0) {
                System.out.println("Course " + c.getCourseID() + " not available.");
                continue;
            }
            coursesRegistered.add(c);
        }
        if (coursesRegistered.size() < 4) {
            for (Course c: student.getSemRegistration().getSecondaryCourses()) {
                if (c.getAvailableSeats() <= 0) {
                    System.out.println("Course " + c.getCourseID() + " not available.");
                    continue;
                }
                if (coursesRegistered.size() >= 4) break;
                coursesRegistered.add(c);
            }
        }

        student.setCourseRegistered(coursesRegistered);
        student.getSemRegistration().setRegDone(true);
        System.out.println("You are now registered for the following courses: ");
        for (Course c: student.getCourseRegistered()) {
            System.out.println(c);
        }
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
        if(!student.getSemRegistration().getRegDone()){
            System.out.println("Please complete semester registration first.");
            return;
        }
        if(student.isFeeDone()){
            System.out.println("Fees already paid!");
            return;
        }
        PaymentService paymentServiceOperation = new PaymentServiceOperation();
        double amountToPay = paymentServiceOperation.calculateAmount();
        System.out.println("Hello, your fees due is " + amountToPay);
        Payment studentPayment = new Payment(student.getUserId());
        System.out.println("How would you like to pay the fees?");
        System.out.println("Press 1 for Online Transaction");
        System.out.println("Press 2 for Offline Transaction");

        System.out.println("Which mode of payment would you like to use?");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if(choice == 1){
            System.out.println("Press 1 for UPI Payment");
            System.out.println("Press 2 for Debit Card");
            System.out.println("Press 3 for Credit Card");
            System.out.println("Press 4 for Net banking");
        }else if(choice == 2){
            System.out.println("Press 5 for Cash Transaction");
            System.out.println("Press 6 for Cheque Transaction");
        }

        int mode = sc.nextInt();
        String message = "";
        switch(mode){
            case 1:
                message = "Fees paid online through"+ " UPI !" ;
                studentPayment.setModeOfPayment(PaymentMode.UPI);
                paymentServiceOperation.payUPI();
                break;
            case 2:
                message = "Fees paid online through"+ " debit card !" ;
                studentPayment.setModeOfPayment(PaymentMode.DEBIT_CARD);
                paymentServiceOperation.payDebitCard();
                break;
            case 3:
                message = "Fees paid online through"+ " credit card !" ;
                studentPayment.setModeOfPayment(PaymentMode.CREDIT_CARD);
                paymentServiceOperation.payCreditCard();
                break;
            case 4:
                studentPayment.setModeOfPayment(PaymentMode.NET_BANKING);
                paymentServiceOperation.payNetBanking();
                break;
            case 5:
                message = "Fees paid offline through"+ " cash !" ;
                studentPayment.setModeOfPayment(PaymentMode.CASH);
                paymentServiceOperation.payCash();
                break;
            case 6:
                message = "Fees paid offline through"+ " cheque !" ;
                studentPayment.setModeOfPayment(PaymentMode.CHEQUE);
                paymentServiceOperation.payCheque();
                break;
            default:
                System.out.println("Invalid key, try again!");
                break;
        }
            if(paymentServiceOperation.paymentApproved()){

                paymentServiceOperation.sendNotification(
                        student.getUserId(),paymentServiceOperation.calculateAmount(), studentPayment.getPaymentId(),message);
            }else{
                System.out.println("Sorry,Payment Failed ! Please try again or contact admin.");
            }
    }

    public GradeCard displayGradeCard() {
        return null;
    }
}
