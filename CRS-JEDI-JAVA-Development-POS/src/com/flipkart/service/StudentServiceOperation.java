package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.constant.PaymentMode;
import com.flipkart.dao.CatalogueDAOImpl;
import com.flipkart.dao.PaymentDAOImpl;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.exception.CourseNotAddedException;
import com.flipkart.exception.CourseNotAvailableException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.PaymentFailedException;
import com.flipkart.utils.Utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class StudentServiceOperation extends UserServiceOperation implements StudentService {
    private Student student;
    private StudentDAOImpl studentDao;

    public StudentServiceOperation() {

    }

    public StudentServiceOperation(Student student) {
        this.student = student;
        this.studentDao = StudentDAOImpl.getInstance(this.student);
        System.out.println(this.studentDao);
    }

    public void registerForSem() {
        student.setSemRegistration(new SemRegistration(student));

        boolean registering = true;
        while (registering) {
            System.out.println();
            System.out.println("**************************************************");
            System.out.println();
            System.out.println("Press 1 to View course catalog");
            System.out.println("Press 2 to Add Primary Courses");
            System.out.println("Press 3 to Add Secondary Courses");
            System.out.println("Press 4 to Remove an added primary course");
            System.out.println("Press 5 to Remove an added secondary course");
            System.out.println("Press 6 to Confirm and proceed with final registration");
            System.out.println("Press 7 to View registered Courses");
            System.out.println("Press 8 to Pay fee");

            boolean addDropWindow = true; // todo change
            if (addDropWindow) {
                System.out.println("Press 9 to Add a course ");
                System.out.println("Press 10 to Delete a Courses");
            }
            System.out.println("Press # to go back to student menu");
            System.out.println();
            System.out.println("**************************************************");
            System.out.println();
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            switch (input) {
                case "1":
                    super.viewCourseCatalogue(false);
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
        System.out.println("Enter your address");
        String address = in.nextLine();
        System.out.println("Enter your mobile number");
        String mobileNumber = in.nextLine();
        while (!Utils.isPhoneNumberValid(mobileNumber)) {
            System.out.println("Your mobile number is invalid. It must a 10 digit numeric. Please enter again:");
            mobileNumber = in.nextLine();
        }
        Date dobParsed = Utils.isDateValid(in);

        Student newStudent = new Student(name, emailEntered, password, departmentId,
                address, mobileNumber, dobParsed);

        newStudent.setUserId("s" + Utils.getStudentCount());

        this.student = newStudent;
        if (this.studentDao == null)
            this.studentDao = StudentDAOImpl.getInstance(this.student);
        this.studentDao.signup();
    }

    public void selectPrimaryCourse() {
        if (this.studentDao.registrationIsDone(student)) return;
        System.out.println("Enter Course Id's to be added as your primary course and press enter.");
        System.out.println("Press # when you are done adding courses!");
        Scanner in = new Scanner(System.in);

        ArrayList<String> primaryCourseIds = this.studentDao.viewPrimaryCourses();

        ArrayList<Course> primaryCourses = new ArrayList<>();
        primaryCourseIds.forEach((courseId) -> {
            if (!(courseId.matches("") || courseId == null))
                primaryCourses.add(Utils.getCourseFromCourseId(courseId));
        });

        System.out.println(primaryCourses);
        while (primaryCourses.size() <= 4) {
            if (primaryCourses.size() == 4) {
                this.studentDao.selectPrimaryCourse(primaryCourses);
                System.out.println("Primary courses added to cart successfully");
                return;
            }
            String input = in.nextLine();
            if (input.matches("#")) {
                if (primaryCourses.size() > 0) {
                    this.studentDao.selectPrimaryCourse(primaryCourses);
                    System.out.println("Primary courses added to cart successfully");
                } else {
                    System.out.println("Exited without adding any courses");
                }
                break;
            } else {
                Course course = Utils.getCourseFromCourseId(input);
                boolean alreadyAdded = false;
                for(Course c : primaryCourses){
                    if(c.getCourseID().equals(course.getCourseID())){
                        System.out.println("Course already exists");
                        alreadyAdded = true;
                        break;
                    }
                }
                if (course == null) {
                    System.out.println("No course with the provided ID!");
                }
                else if (course.getProfessorID().matches("") || course.getProfessorID() == null) {
                    System.out.println("No professor assigned to this course yet. Please select another");
                } else if(!alreadyAdded) {
                    primaryCourses.add(course);
                    System.out.println("Course " + course.getCourseID() + " is added successfully to your cart!");
                }
            }
        }
    }

    public void removeCourseFromCart(String type) {
        if (this.studentDao.registrationIsDone(student)) return;
        List<String> courseIds = (type.matches("primary")) ?
                this.studentDao.viewPrimaryCourses() :
                this.studentDao.viewSecondaryCourses();

        ArrayList<Course> courses = new ArrayList<>();
        courseIds.forEach((courseId) -> {
            if (!(courseId.matches("") || courseId == null))
                courses.add(Utils.getCourseFromCourseId(courseId));
        });

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

        if (type.matches("primary")) {
            this.studentDao.selectPrimaryCourse(courses);
            System.out.println("Course " + toBeDeleted + " removed from your primary courses");
        } else if (type.matches("secondary")) {
            this.studentDao.selectSecondaryCourse(courses);
            System.out.println("Course " + toBeDeleted + " removed from your secondary courses");
        }
    }


    public void selectSecondaryCourse() {
        if (this.studentDao.registrationIsDone(student)) return;
        System.out.println("Enter Course Id's to be added as your secondary course and press enter.");
        System.out.println("Press # when you are done adding courses!");
        Scanner in = new Scanner(System.in);

        ArrayList<String> courseIds = this.studentDao.viewSecondaryCourses();
        ArrayList<Course> secondaryCourses = new ArrayList<>();
        courseIds.forEach((courseId) -> {
            if (!(courseId.matches("") || courseId == null))
                secondaryCourses.add(Utils.getCourseFromCourseId(courseId));
        });

        while (secondaryCourses.size() <= 2) {
            if (secondaryCourses.size() == 2) {
                this.studentDao.selectSecondaryCourse(secondaryCourses);
                System.out.println("Secondary courses added to cart successfully");
                return;
            }
            String input = in.nextLine();
            if (input.matches("#")) {
                if (secondaryCourses.size() > 0) {
                    this.studentDao.selectSecondaryCourse(secondaryCourses);
                    System.out.println("Secondary courses added to cart successfully");
                } else {
                    System.out.println("Exited without adding any courses");
                }
                break;
            } else {
                Course course = Utils.getCourseFromCourseId(input);
                if (course == null) {
                    System.out.println("No course with the provided ID!");
                } else if (course.getProfessorID().matches("") || course.getProfessorID() == null) {
                    System.out.println("No professor assigned to this course yet. Please select another");
                } else {
                    secondaryCourses.add(course);
                    System.out.println("Course " + course.getCourseID() + " is added successfully to your (secondary) cart!");
                }
            }
        }
    }

    public void confirmRegistration() {
        if (this.studentDao.registrationIsDone(student)) return;
        // Proceed with business logic for course verification.
        ArrayList<String> primaryCourseIds = this.studentDao.viewPrimaryCourses();
        ArrayList<String> secondaryCourseIds = this.studentDao.viewSecondaryCourses();
        ArrayList<String> registeredCourseIds = this.studentDao.displayRegisteredCourses();
        System.out.println(registeredCourseIds);
        System.out.println(registeredCourseIds.size());
        ArrayList<Course> primaryCourses = new ArrayList<>();
        ArrayList<Course> secondaryCourses = new ArrayList<>();
        ArrayList<Course> registeredCourses = new ArrayList<>();

        primaryCourseIds.forEach((courseId) -> {
            if (!(courseId.matches("") || courseId == null))
                primaryCourses.add(Utils.getCourseFromCourseId(courseId));
        });
        secondaryCourseIds.forEach((courseId) -> {
            if (!(courseId.matches("") || courseId == null))
                secondaryCourses.add(Utils.getCourseFromCourseId(courseId));
        });
        registeredCourseIds.forEach((courseId) -> {
            if (!(courseId.matches("") || courseId == null))
                registeredCourses.add(Utils.getCourseFromCourseId(courseId));
        });

        for (Course c : primaryCourses) {
            if (c.getAvailableSeats() <= 0) {
                System.out.println("Primary Course " + c.getCourseID() + " not available.");
            } else {
                System.out.println("Primary Course " + c.getCourseID() + " allotted for you!");
                registeredCourses.add(c);
            }
        }

        if (registeredCourses.size() < 4) {
            for (Course c : secondaryCourses) {
                if (c.getAvailableSeats() <= 0) {
                    System.out.println("Secondary Course " + c.getCourseID() + " not available.");
                    continue;
                }
                if (registeredCourses.size() >= 4) break;
                registeredCourses.add(c);
            }
        }
        student.getSemRegistration().setRegDone(true);
        System.out.println("You are now registered for the following courses: ");
        for (Course c : registeredCourses) {
            Utils.updateCourseSeats(c);
            c.setAvailableSeats(c.getAvailableSeats() - 1);
            System.out.println(c);
        }
        studentDao.setRegisteredCourses(registeredCourses);
        studentDao.confirmRegistration();
    }

    public void addCourse() {
        Scanner in = new Scanner(System.in);

        if (student.getCourseRegistered().size() >= 4) {
            System.out.println("Course limit reached, please drop a course to add another");
            return;
        }

        System.out.println("Enter course Id to add : ");
        String courseToAdd = in.nextLine();
        if (Utils.getCourseFromCourseId(courseToAdd) == null) {
            System.out.println("No course with the given ID!");
            return;
        }

        List<Course> courses = new CatalogueDAOImpl().fetchCatalogue(false);
        boolean isAdded = false, isFound = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseID().matches(courseToAdd)) {
                isFound = true;
            }
            if (courses.get(i).getAvailableSeats() > 0) {
                studentDao.addCourse(courses.get(i).getCourseID());
                isAdded = true;
                break;
            } else {
                try {
                    throw new CourseNotAvailableException(courseToAdd);
                } catch (CourseNotAvailableException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        if (!isFound) {
            try {
                throw new CourseNotFoundException(courseToAdd);
            } catch (CourseNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!isAdded) {
            try {
                throw new CourseNotAddedException(courseToAdd);
            } catch (CourseNotAddedException e) {
                throw new RuntimeException(e);
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
            studentDao.dropCourse(toBeDropped.getCourseID());
            System.out.println("Course dropped successfully");
        } else {
            System.out.println("No such registered course exists");
            try {
                throw new CourseNotFoundException(courseId);
            } catch (CourseNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void payFee() {
        if (!this.studentDao.registrationIsDone(student)) {
            System.out.println("Please complete semester registration first.");
            return;
        }
        if (student.isFeeDone()) {
            System.out.println("Fees already paid!");
            return;
        }
        PaymentService paymentServiceOperation = new PaymentServiceOperation(student);
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

        boolean isFeeDone = false;

        switch (mode) {
            case 1:
                message = "Fees paid online through" + " UPI !";
                studentPayment.setModeOfPayment(PaymentMode.UPI);
                isFeeDone = PaymentDAOImpl.getInstance().payUPI(student);
                break;
            case 2:
                message = "Fees paid online through" + " debit card !";
                studentPayment.setModeOfPayment(PaymentMode.DEBIT_CARD);
                isFeeDone = PaymentDAOImpl.getInstance().payDebitCard(student);
                break;
            case 3:
                message = "Fees paid online through" + " credit card !";
                studentPayment.setModeOfPayment(PaymentMode.CREDIT_CARD);
                isFeeDone = PaymentDAOImpl.getInstance().payCreditCard(student);
                break;
            case 4:
                studentPayment.setModeOfPayment(PaymentMode.NET_BANKING);
                isFeeDone = PaymentDAOImpl.getInstance().payNetBanking(student);
                break;
            case 5:
                message = "Fees paid offline through" + " cash !";
                studentPayment.setModeOfPayment(PaymentMode.CASH);
                isFeeDone = PaymentDAOImpl.getInstance().payCash(student);
                break;
            case 6:
                message = "Fees paid offline through" + " cheque !";
                studentPayment.setModeOfPayment(PaymentMode.CHEQUE);
                isFeeDone = PaymentDAOImpl.getInstance().payCheque(student);
                break;
            default:
                System.out.println("Invalid key, try again!");
                break;
        }

        if (isFeeDone) {
            student.setFeeDone(true);
        } else {
            try {
                throw new PaymentFailedException(student.getUserId());
            } catch (PaymentFailedException e) {
                System.out.println(e.getMessage());
            }
        }


        if (PaymentDAOImpl.getInstance().paymentApproved(student)) {

            PaymentDAOImpl.getInstance().sendNotification(
                    student.getUserId(), paymentServiceOperation.calculateAmount(), message);
        } else {
            System.out.println("Sorry,Payment Failed ! Please try again or contact admin.");
        }
    }

    public void displayRegisteredCourses() {
        ArrayList<String> enrolledCourses = this.studentDao.displayRegisteredCourses();
        if (enrolledCourses == null) return;
        System.out.println("Your enrolled courses are: ");
        enrolledCourses.forEach((courseId) -> {
            Course c = Utils.getCourseFromCourseId(courseId);
            System.out.println(c);
        });
    }

    public void displayGradeCard() {
        this.studentDao.displayGradeCard();
    }
}
