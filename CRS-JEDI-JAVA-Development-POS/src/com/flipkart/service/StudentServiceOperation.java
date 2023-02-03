package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.constant.PaymentMode;
import com.flipkart.data.CourseData;
import com.flipkart.data.UserData;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class StudentServiceOperation extends UserServiceOperation implements StudentService {
    private Student student;

    public StudentServiceOperation() {
    }

    public StudentServiceOperation(Student student) {
        this.student = student;
    }

    public void registerForSem() {
        student.setSemRegistration(new SemRegistration(student));

        boolean registering = true;
        while (registering) {
            System.out.println();
            System.out.println("**************************************************");
            System.out.println();
            System.out.println("Press 1 to view course catalog");
            System.out.println("Press 2 to add Primary Courses");
            System.out.println("Press 3 to add Secondary Courses");
            System.out.println("Press 4 to remove an added primary course");
            System.out.println("Press 5 to remove an added secondary course");
            System.out.println("Press 6 to confirm and proceed with final registration");
            System.out.println("Press 7 to view registered Courses");
            System.out.println("Press 8 to pay fee");

            boolean addDropWindow = true; // todo change
            if (addDropWindow) {
                System.out.println("Press 9 to add a course ");
                System.out.println("Press 10 to delete a Courses");
            }
            System.out.println("Press # to go back to student menu");
            System.out.println();
            System.out.println("**************************************************");
            System.out.println();
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            switch (input) {
                case "1":
                    super.viewCourseCatalogue();
                    break;
                case "2":
                    selectPrimaryCourse();
                    break;
                case "3":
                    selectSecondaryCourse();
                    break;
                case "4":
                    removeCourseFromCart("primary");
                    break;
                case "5":
                    removeCourseFromCart("secondary");
                    break;
                case "6":
                    confirmRegistration();
                    break;
                case "7":
                    displayRegisteredCourses();
                    break;
                case "8":
                    payFee();
                    break;
                case "9":
                    addCourse();
                    return;
                case "10":
                    dropCourse();
                    break;
                case "#":
                    registering = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void signup() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = in.nextLine();
        System.out.println("Enter your Email Id");
        String emailEntered = in.nextLine();
        System.out.println("Enter your password");
        String password = in.nextLine();
        System.out.println("Enter your Department Id");
        String departmentId = in.nextLine();
        Student newStudent = new Student(name, emailEntered, password, departmentId);
        newStudent.setUserId(Integer.toString(new Random().nextInt(100)));
        UserData.studentList.add(newStudent);
    }

    public void selectPrimaryCourse() {
        System.out.println("Enter Course Id's to be added as your primary course and press enter.");
        System.out.println("Press # when you are done adding courses!");
        Scanner in = new Scanner(System.in);
        ArrayList<Course> primaryCourses = student.getSemRegistration().getPrimaryCourses();

        while (primaryCourses.size() <= 4) {
            if (primaryCourses.size() == 4) {
                student.getSemRegistration().setPrimaryCourses(primaryCourses);
                System.out.println("Primary courses added to cart successfully");
                return;
            }
            String input = in.nextLine();
            if (input.matches("#")) {
                if (primaryCourses.size() > 0) {
                    student.getSemRegistration().setPrimaryCourses(primaryCourses);
                    System.out.println("Primary courses added to cart successfully");
                } else {
                    System.out.println("Exited without adding any courses");
                }
                break;
            } else {
                Course course = getCourseFromId(input);
                if (course == null) {
                    System.out.println("No course with the provided ID!");
                } else {
                    primaryCourses.add(course);
                    System.out.println("Course " + course.getCourseID() + " is added successfully to your cart!");
                }
            }
        }
    }

    public void removeCourseFromCart(String type) {
        List<Course> courses = (type.matches("primary")) ?
                student.getSemRegistration().getPrimaryCourses() :
                student.getSemRegistration().getSecondaryCourses();

        if (courses.size() == 0) {
            System.out.println("You have not added any " + type + " courses yet");
            return;
        }
        System.out.println("Please enter the Course ID of the course to remove");
        Scanner in = new Scanner(System.in);
        String courseId = in.nextLine();
        Course toBeDeleted = null;
        for (Course c : courses) {
            if (c.getCourseID().matches(courseId)) {
                toBeDeleted = c;
                break;
            }
        }
        if (toBeDeleted == null) {
            System.out.println("You have not added " + courseId + " to your primary courses!");
            return;
        }
        courses.remove(toBeDeleted);

        if (type.matches("primary"))
            System.out.println("Course " + toBeDeleted + " removed from your primary courses");
        else if (type.matches("secondary"))
            System.out.println("Course " + toBeDeleted + " removed from your secondary courses");
    }

    private Course getCourseFromId(String courseId) {
        for (Course c : CourseData.courseList) {
            if (c.getCourseID().matches(courseId)) {
                return c;
            }
        }
        return null;
    }

    public void selectSecondaryCourse() {
        System.out.println("Enter Course Id's to be added as your secondary course and press enter.");
        System.out.println("Press # when you are done adding courses!");
        Scanner in = new Scanner(System.in);
        ArrayList<Course> secondaryCourses = student.getSemRegistration().getSecondaryCourses();

        while (secondaryCourses.size() <= 2) {
            if (secondaryCourses.size() == 2) {
                student.getSemRegistration().setPrimaryCourses(secondaryCourses);
                System.out.println("Secondary courses added to cart successfully");
                return;
            }
            String input = in.nextLine();
            if (input.matches("#")) {
                if (secondaryCourses.size() > 0) {
                    student.getSemRegistration().setSecondaryCourses(secondaryCourses);
                    System.out.println("Secondary courses added to cart successfully");
                } else {
                    System.out.println("Exited without adding any courses");
                }
                break;
            } else {
                Course course = getCourseFromId(input);
                if (course == null) {
                    System.out.println("No course with the provided ID!");
                } else {
                    secondaryCourses.add(course);
                    System.out.println("Course " + course.getCourseID() + " is added successfully to your (secondary) cart!");
                }
            }
        }
    }

    public void confirmRegistration() {
        // Proceed with business logic for course verification.
        for (Course c : student.getSemRegistration().getPrimaryCourses()) {
            if (c.getAvailableSeats() <= 0) {
                System.out.println("Primary Course " + c.getCourseID() + " not available.");
            } else {
                System.out.println("Primary Course " + c.getCourseID() + " allotted for you!");
                student.getCourseRegistered().add(c);
            }
        }

        if (student.getCourseRegistered().size() < 4) {
            for (Course c : student.getSemRegistration().getSecondaryCourses()) {
                if (c.getAvailableSeats() <= 0) {
                    System.out.println("Secondary Course " + c.getCourseID() + " not available.");
                    continue;
                }
                if (student.getCourseRegistered().size() >= 4) break;
                student.getCourseRegistered().add(c);
            }
        }
        student.getSemRegistration().setRegDone(true);
        System.out.println("You are now registered for the following courses: ");
        for (Course c : student.getCourseRegistered()) {
            c.setAvailableSeats(c.getAvailableSeats() - 1);
            System.out.println(c);
        }
    }

    public void addCourse() {
        Scanner in = new Scanner(System.in);

        if (student.getCourseRegistered().size() >= 4) {
            System.out.println("Course limit reached, please drop a course to add another");
            return;
        }

        System.out.println("Enter course Id to add : ");
        String courseToAdd = in.nextLine();
        if (getCourseFromId(courseToAdd) == null) {
            System.out.println("No course with the given ID!");
            return;
        }

        List<Course> courses = CourseData.courseList;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseID().matches(courseToAdd) && courses.get(i).getAvailableSeats() > 0) {
                List<Course> courselist = student.getCourseRegistered();
                courselist.add(courses.get(i));
                courses.get(i).setAvailableSeats(courses.get(i).getAvailableSeats() - 1);
                student.setCourseRegistered(courselist);
                System.out.println("Course added successfully ");

                return;
            }
        }
    }

    public void dropCourse() {
        System.out.println("Enter course Id to drop : ");
        Scanner in = new Scanner(System.in);
        String courseId = in.nextLine();

        Course toBeDropped = null;
        for (Course c : student.getCourseRegistered()) {
            if (c.getCourseID().matches(courseId)) {
                toBeDropped = c;
                break;
            }
        }
        if (toBeDropped != null) {
            student.getCourseRegistered().remove(toBeDropped);
            System.out.println("Course dropped successfully");
        } else {
            System.out.println("No such registered course exists");
        }
    }

    public void payFee() {
        if (!student.getSemRegistration().getRegDone()) {
            System.out.println("Please complete semester registration first.");
            return;
        }
        if (student.isFeeDone()) {
            System.out.println("Fees already paid!");
            return;
        }
        PaymentService paymentServiceOperation = new PaymentServiceOperation();
        double amountToPay = paymentServiceOperation.calculateAmount();
        System.out.println("Hello, your fees due is " + amountToPay);
        Payment studentPayment = new Payment(student.getUserId());
        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("How would you like to pay the fees?");
        System.out.println("Press 1 for Online Transaction");
        System.out.println("Press 2 for Offline Transaction");
        System.out.println();
        System.out.println("**************************************************");
        System.out.println();

        System.out.println("Which mode of payment would you like to use?");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        if (choice == 1) {
            System.out.println("Press 1 for UPI Payment");
            System.out.println("Press 2 for Debit Card");
            System.out.println("Press 3 for Credit Card");
            System.out.println("Press 4 for Net banking");
        } else if (choice == 2) {
            System.out.println("Press 5 for Cash Transaction");
            System.out.println("Press 6 for Cheque Transaction");
        }
        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        int mode = sc.nextInt();
        String message = "";
        switch (mode) {
            case 1:
                message = "Fees paid online through" + " UPI !";
                studentPayment.setModeOfPayment(PaymentMode.UPI);
                paymentServiceOperation.payUPI();
                break;
            case 2:
                message = "Fees paid online through" + " debit card !";
                studentPayment.setModeOfPayment(PaymentMode.DEBIT_CARD);
                paymentServiceOperation.payDebitCard();
                break;
            case 3:
                message = "Fees paid online through" + " credit card !";
                studentPayment.setModeOfPayment(PaymentMode.CREDIT_CARD);
                paymentServiceOperation.payCreditCard();
                break;
            case 4:
                studentPayment.setModeOfPayment(PaymentMode.NET_BANKING);
                paymentServiceOperation.payNetBanking();
                break;
            case 5:
                message = "Fees paid offline through" + " cash !";
                studentPayment.setModeOfPayment(PaymentMode.CASH);
                paymentServiceOperation.payCash();
                break;
            case 6:
                message = "Fees paid offline through" + " cheque !";
                studentPayment.setModeOfPayment(PaymentMode.CHEQUE);
                paymentServiceOperation.payCheque();
                break;
            default:
                System.out.println("Invalid key, try again!");
                break;
        }
        if (paymentServiceOperation.paymentApproved()) {

            paymentServiceOperation.sendNotification(
                    student.getUserId(), paymentServiceOperation.calculateAmount(), studentPayment.getPaymentId(), message);
        } else {
            System.out.println("Sorry,Payment Failed ! Please try again or contact admin.");
        }
    }

    public void displayRegisteredCourses() {
        System.out.println("List of Registered courses:");
        int i = 1;
        boolean flag = true;
        for (Course c : student.getCourseRegistered()) {
            System.out.println(i + ". Course Name :" + c.getName() + ", Course ID : " + c.getCourseID());
            i++;
            flag = false;
        }
        if (flag) {
            System.out.println("There are no Registered Courses");
        }
    }

    public GradeCard displayGradeCard() {
        if (!student.isGradeCardApproved()) {
            System.out.println("Your gradeCard has not been generated yet. Please contact your admin.");
            return null;
        }
        GradeCard gradeCard = student.getGradeCard();
        System.out.println("Student id: " + gradeCard.getStudentID());
        System.out.println("Semester id: " + gradeCard.getSemesterID());
        System.out.println("SGPA : " + gradeCard.getSGPA());
        System.out.println("Courses :" + gradeCard.getCourseList());
        System.out.print("Grades :");
        for (Grade g : gradeCard.getGrades()) {
            System.out.print(g.getGrade() + ", ");
        }

        return gradeCard;
    }
}
