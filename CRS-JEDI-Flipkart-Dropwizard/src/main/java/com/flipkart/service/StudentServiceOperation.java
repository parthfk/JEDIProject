package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.constant.PaymentMode;
import com.flipkart.dao.CatalogueDAOImpl;
import com.flipkart.dao.PaymentDAOImpl;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.exception.*;
import com.flipkart.utils.Utils;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class StudentServiceOperation extends UserServiceOperation implements StudentService {
    private Student student;
    private StudentDAOImpl studentDao;


    private static StudentServiceOperation operation;
    public static StudentServiceOperation getInstance() {
        if (operation == null) operation = new StudentServiceOperation();
        return operation;
    }
    public StudentServiceOperation() {
        this.studentDao = StudentDAOImpl.getInstance(this.student);
    }

    public String getStudent() {
        return student.getDepartmentID();
    }

    public StudentDAOImpl getDao() {return this.studentDao;}

    public void setStudent(Student student) {
        this.studentDao.setStudent(student);
        this.student = student;
        System.out.println("\n\n\n\n\n"+ this.studentDao.getStudent().getDepartmentID() + "\n\n\n\nn");
    }

    public StudentServiceOperation(Student student) {
        this.student = student;
        this.studentDao = StudentDAOImpl.getInstance(this.student);
        System.out.println(this.studentDao);
    }

//    public void registerForSem() {
//        student.setSemRegistration(new SemRegistration(student));
//
//        boolean registering = true;
//        while (registering) {
//            System.out.println();
//            System.out.println("**************************************************");
//            System.out.println();
//            System.out.println("Press 1 to View course catalog");
//            System.out.println("Press 2 to Add Primary Courses");
//            System.out.println("Press 3 to Add Secondary Courses");
//            System.out.println("Press 4 to Remove an added primary course");
//            System.out.println("Press 5 to Remove an added secondary course");
//            System.out.println("Press 6 to Confirm and proceed with final registration");
//            System.out.println("Press 7 to View registered Courses");
//            System.out.println("Press 8 to Pay fee");
//
//            boolean addDropWindow = true; // todo change
//            if (addDropWindow) {
//                System.out.println("Press 9 to Add a course ");
//                System.out.println("Press 10 to Delete a Courses");
//            }
//            System.out.println("Press # to go back to student menu");
//            System.out.println();
//            System.out.println("**************************************************");
//            System.out.println();
//            Scanner in = new Scanner(System.in);
//            String input = in.nextLine();
//            switch (input) {
//                case "1":
//                    super.viewCourseCatalogue(false);
//                    break;
//                case "2":
//                    selectPrimaryCourse();
//                    break;
//                case "3":
//                    selectSecondaryCourse();
//                    break;
//                case "4":
//                    removeCourseFromCart("primary");
//                    break;
//                case "5":
//                    removeCourseFromCart("secondary");
//                    break;
//                case "6":
//                    confirmRegistration();
//                    break;
//                case "7":
//                    displayRegisteredCourses();
//                    break;
//                case "8":
//                    payFee();
//                    break;
//                case "9":
//                    addCourse();
//                    return;
//                case "10":
//                    dropCourse();
//                    break;
//                case "#":
//                    registering = false;
//                    break;
//                default:
//                    break;
//            }
//        }
//    }


    public void signup(Student newStudent) {
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter your name");
//        String name = in.nextLine();
//        System.out.println("Enter your Email Id");
//        String emailEntered = in.nextLine();
//        System.out.println("Enter your password");
//        String password = in.nextLine();
//        System.out.println("Enter your Department Id");
//        String departmentId = in.nextLine();
//        System.out.println("Enter your address");
//        String address = in.nextLine();
//        System.out.println("Enter your mobile number");
//        String mobileNumber = in.nextLine();
//        while (!Utils.isPhoneNumberValid(mobileNumber)) {
//            System.out.println("Your mobile number is invalid. It must a 10 digit numeric. Please enter again:");
//            mobileNumber = in.nextLine();
//        }
//        Date dobParsed = Utils.isDateValid(in);
//
//        Student newStudent = new Student(name, emailEntered, password, departmentId,
//                address, mobileNumber, dobParsed);

        newStudent.setUserId("s" + Utils.getStudentCount());

        this.student = newStudent;
        if (this.studentDao == null)
            this.studentDao = StudentDAOImpl.getInstance(this.student);
        this.studentDao.signup(newStudent);
    }

    public void selectPrimaryCourse(String studentId, String pc1, String pc2, String pc3, String pc4) {
        //if (this.studentDao.registrationIsDone(student)) return;

        System.out.println("Setting " + pc1);
        System.out.println("Setting " + pc2);
        System.out.println("Setting " + pc3);
        System.out.println("Setting " + pc4);
        ArrayList<String> primaryCourses = new ArrayList<>();
        primaryCourses.add(pc1);
        primaryCourses.add(pc2);
        primaryCourses.add(pc3);
        primaryCourses.add(pc4);
        this.studentDao.selectPrimaryCourse(studentId, primaryCourses);
    }

    public void removeCourseFromCart(String studentId, String type, String courseId) {
        //if (this.studentDao.registrationIsDone(student)) return;
        ArrayList<String> courseIds = (type.matches("primary")) ?
                this.studentDao.viewPrimaryCourses(studentId) :
                this.studentDao.viewSecondaryCourses(studentId);

        if (courseIds.size() == 0) {
            System.out.println("You have not added any " + type + " courses yet");
            return;
        }
        System.out.println("Please enter the Course ID of the course to remove");
        String toBeDeleted = null;
        for (String c : courseIds) {
            if (c.matches(courseId)) {
                toBeDeleted = c;
                break;
            }
        }
        if (toBeDeleted == null || toBeDeleted.matches("")) {
            System.out.println("You have not added " + courseId + " to your primary courses!");
            return;
        }
        courseIds.remove(toBeDeleted);

        if (type.matches("primary")) {
            this.studentDao.selectPrimaryCourse(studentId, courseIds);
            System.out.println("Course " + toBeDeleted + " removed from your primary courses");
        } else if (type.matches("secondary")) {
            this.studentDao.selectSecondaryCourse(studentId, courseIds);
            System.out.println("Course " + toBeDeleted + " removed from your secondary courses");
        }
    }


    public void selectSecondaryCourse(String studentId, String sc1, String sc2) {
        //f (this.studentDao.registrationIsDone(student)) return;


        ArrayList<String> secondaryCourses = new ArrayList<>();
        secondaryCourses.add(sc1);
        secondaryCourses.add(sc2);
        this.studentDao.selectSecondaryCourse(studentId, secondaryCourses);
    }

    public void confirmRegistration(String studentId) {
        //if (this.studentDao.registrationIsDone(student)) return;
        // Proceed with business logic for course verification.
        ArrayList<String> primaryCourseIds = this.studentDao.viewPrimaryCourses(studentId);
        ArrayList<String> secondaryCourseIds = this.studentDao.viewSecondaryCourses(studentId);
        ArrayList<String> registeredCourseIds = this.studentDao.displayRegisteredCourses(studentId);
        ArrayList<Course> primaryCourses = new ArrayList<>();
        ArrayList<Course> secondaryCourses = new ArrayList<>();
        ArrayList<Course> registeredCourses = new ArrayList<>();

        System.out.println(primaryCourseIds);
        System.out.println(secondaryCourseIds);
        System.out.println(registeredCourseIds);


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
        System.out.println("You are now registered for the following courses: ");
        for (Course c : registeredCourses) {
            Utils.updateCourseSeats(c);
            c.setAvailableSeats(c.getAvailableSeats() - 1);
            System.out.println(c);
        }
        studentDao.setRegisteredCourses(studentId, registeredCourses);
        studentDao.confirmRegistration(studentId);
    }

    public void addCourse() throws SQLException {
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

        List<Course> courses;
        try {
            courses = new CatalogueDAOImpl().fetchCatalogue(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void payFee(String studentId, String modeOfPayment,
                       String upiId, String cardNumber, String expiryDate, String cvv, String bankName
    ,String accId, String password, String checkNo, String receiptNo) {
//        if (!this.studentDao.registrationIsDone(student)) {
//            System.out.println("Please complete semester registration first.");
//            return;
//        }
//        if (student.isFeeDone()) {
//            System.out.println("Fees already paid!");
//            return;
//        }
        PaymentService paymentServiceOperation = new PaymentServiceOperation(student);
        double amountToPay = paymentServiceOperation.calculateAmount();
        Payment studentPayment = new Payment(studentId);
        String message = "";

        boolean isFeeDone = false;

        switch (modeOfPayment) {
            case "upi":
                message = "Fees paid online through" + " UPI !";
                studentPayment.setModeOfPayment(PaymentMode.UPI);
                isFeeDone = PaymentDAOImpl.getInstance().payUPI(studentId, upiId);
                break;
            case "debitCard":
                message = "Fees paid online through" + " debit card !";
                studentPayment.setModeOfPayment(PaymentMode.DEBIT_CARD);
                isFeeDone = PaymentDAOImpl.getInstance().payDebitCard(student);
                break;
            case "creditCard":
                message = "Fees paid online through" + " credit card !";
                studentPayment.setModeOfPayment(PaymentMode.CREDIT_CARD);
                isFeeDone = PaymentDAOImpl.getInstance().payCreditCard(student);
                break;
            case "netBanking":
                studentPayment.setModeOfPayment(PaymentMode.NET_BANKING);
                isFeeDone = PaymentDAOImpl.getInstance().payNetBanking(student);
                break;
            case "cash":
                message = "Fees paid offline through" + " cash !";
                studentPayment.setModeOfPayment(PaymentMode.CASH);
                isFeeDone = PaymentDAOImpl.getInstance().payCash(student);
                break;
            case "check":
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
            System.out.println("Sorry, Payment Failed ! Please try again or contact admin.");
        }
    }

    public void displayRegisteredCourses() {
        ArrayList<String> enrolledCourses = this.studentDao.displayRegisteredCourses(student.getUserId());
        if (enrolledCourses == null) return;
        System.out.println("Your enrolled courses are: ");
        enrolledCourses.forEach((courseId) -> {
            Course c = Utils.getCourseFromCourseId(courseId);
            System.out.println(c);
        });
    }

    public String displayGradeCard(String studentId) {
        return this.studentDao.displayGradeCard(studentId);
    }

    @Override
    public boolean logOut(User user) {
        return false;
    }

    @Override
    public boolean updatePassword(String inputEmail, String oldPassword, String newPassword, String role) throws UserNotFoundException, PasswordMismatchException, SQLException {
        return false;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return null;
    }
}
