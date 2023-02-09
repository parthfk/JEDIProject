package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.CatalogueDAOImpl;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.*;
import com.flipkart.utils.Utils;

import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.*;

public class UserServiceOperation implements UserService {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public User logIn() {
        Scanner in = new Scanner(System.in);
        boolean emailValidated = false, passWordEnteredIsCorrect = false;
        User userObj = null;

        //List<User> userData = null;
        System.out.println("Please enter your Email id: ");
        String inputEmail = in.nextLine().toLowerCase();
        System.out.println("Please enter your Password: ");
        String passwordEntered = in.nextLine().toLowerCase();
        System.out.println("Please enter your Role: ");
        String role = in.nextLine().toLowerCase();

        while (!emailValidated) {
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(inputEmail);

            if (matcher.matches()) {
                try {
                    if (role.equals("admin")) {
                        List<Admin> adminList = Utils.getAdminList();
                        for (Admin u : adminList) {
                            if (u.getEmail().equals(inputEmail)) {
                                userObj = u;
                                emailValidated = true;
                                break;
                            }
                        }
                        if (!emailValidated) {
                            try {
                                throw new AdminNotFoundException(inputEmail);
                            } catch (AdminNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            checkUserExists(inputEmail);
                            return null;
                        }
                    } else if (role.equals("student")) {
                        List<Student> studentList = Utils.getStudentList();
                        for (Student u : studentList) {
                            if (u.getEmail().equals(inputEmail)) {
                                userObj = u;
//                                System.out.println(u.getEmail());
//                                System.out.println(u.isStatusApproval());
                                if (!u.isStatusApproval()) {
                                    System.out.println("Registration not approved. Please contact admin");
                                    try {
                                        throw new StudentNotApprovedException(inputEmail);
                                    } catch (StudentNotApprovedException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    return null;
                                }
                                emailValidated = true;
                                break;
                            }
                        }
                        if (!emailValidated) {
                            try {
                                throw new StudentNotFoundException(inputEmail);
                            } catch (StudentNotFoundException e) {
                                System.out.println(e.getMessage());
                            }

                            checkUserExists(inputEmail);
                            return null;
                        }
                    } else if (role.equals("professor")) {
                        List<Professor> professorList = Utils.getProfessorList();
                        boolean flag = false;
                        assert professorList != null;
                        for (Professor u : professorList) {
                            if (u.getEmail().equals(inputEmail)) {
                                userObj = u;
                                emailValidated = true;
                                //flag = true;
                                break;
                            }
                        }
                        if (!emailValidated) {
                            try {
                                throw new ProfessorNotFoundException(inputEmail);
                            } catch (ProfessorNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            checkUserExists(inputEmail);
                            return null;
                        }
                    } else {
                        System.out.println(ANSI_YELLOW+
                                "Please enter a Valid role, which can be 'student', 'professor' or 'admin'!"+
                                ANSI_RESET);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Failure in reading the db : \n" + e.getMessage());
                }
            } else {
                System.out.println(ANSI_YELLOW+
                        "Invalid formatted email!"+
                        ANSI_RESET);
                return null;
            }
        }

        while (!passWordEnteredIsCorrect) {
            if (passwordEntered.equals(userObj.getPassword())) {
                passWordEnteredIsCorrect = true;
            } else {
                System.out.println(ANSI_YELLOW+
                        "Password is incorrect !"+
                        ANSI_RESET);
                return null;
            }
        }
        return userObj;
    }

    private void checkUserExists(String inputEmail) {
        List<User> userList = Utils.getUserList();
        for (User u : userList) {
            if (u.getEmail().matches(inputEmail)) {
                System.out.println(ANSI_YELLOW+
                        "You have entered the wrong role. Please try again, entering the appropriate role"+
                        ANSI_RESET);
                System.out.println("Your role is " + u.getUserType());
                return;
            }
        }
        System.out.println(ANSI_YELLOW+
                "No user exists with this email! Please contact admin for help!"+
                ANSI_RESET);
    }

    public boolean logOut(User user) {
        System.out.println("You have logged out successfully at " + new Date());
        return true;
    }

    public List<Course> viewCourseCatalogue(boolean viewAll) {
        List<Course> courses = new CatalogueDAOImpl().fetchCatalogue(viewAll);

        if(courses.size()==0)
        {
            System.out.println(ANSI_YELLOW+
                    "No Courses exist in Catalogue"+
                    ANSI_RESET);
            return courses;


        }
        System.out.println("These are the courses currently available: ");
            StringBuffer buffer = new StringBuffer();
            Formatter fmt = new Formatter();

            fmt.format("\n%17s %17s %17s\n", ANSI_CYAN+"Course ID"+ANSI_RESET, ANSI_CYAN+"Course Name"+ANSI_RESET,ANSI_CYAN+ "Professor"+ANSI_RESET);

            for(Course c: courses)
            {
                fmt.format("%17s %17s %17s\n", ANSI_RESET+c.getCourseID()+ANSI_RESET,ANSI_RESET+c.getName()+ANSI_RESET,ANSI_RESET+c.getProfessorID()+ANSI_RESET);
            }
            System.out.println(fmt);
            buffer.setLength(0);

        return courses;

    }

    public boolean updatePassword() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter your Role: ");
        String role = in.nextLine().toLowerCase();

        boolean emailValidated = false, passWordEnteredIsCorrect = false;
        String inputEmail = null, passwordEntered = null;
        User userObj = null;
        while (!emailValidated) {
            System.out.println("Please enter your emailId: (or press 1 to exit)");
            inputEmail = in.nextLine();
            if (inputEmail.equals("1")) {
                return false;
            }

            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(inputEmail);

            if (matcher.matches()) {
                try {
                    if (role.equals("admin")) {
                        List<Admin> adminList = Utils.getAdminList();
                        for (User u : adminList) {
                            if (u.getEmail().equals(inputEmail)) {
                                userObj = u;
                                emailValidated = true;
                                break;
                            }
                        }
                        if (!emailValidated) {
                            try {
                                throw new AdminNotFoundException(inputEmail);
                            } catch (AdminNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            checkUserExists(inputEmail);
                            return false;
                        }
                    } else if (role.equals("student")) {
                        List<Student> studentList = Utils.getStudentList();
                        for (User u : studentList) {
                            if (u.getEmail().equals(inputEmail)) {
                                userObj = u;
                                emailValidated = true;
                                break;
                            }
                        }
                        if (!emailValidated) {
                            try {
                                throw new StudentNotFoundException(inputEmail);
                            } catch (StudentNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            checkUserExists(inputEmail);
                            return false;
                        }

                    } else if (role.equals("professor")) {
                        List<Professor> professorList = Utils.getProfessorList();
                        for (User u : professorList) {
                            if (u.getEmail().equals(inputEmail)) {
                                userObj = u;
                                emailValidated = true;
                                break;
                            }
                        }
                        if (!emailValidated) {
                            try {
                                throw new ProfessorNotFoundException(inputEmail);
                            } catch (ProfessorNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            checkUserExists(inputEmail);
                            return false;
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("Error in reading db :" + e.getMessage());
                }
            }
            if (!emailValidated)
                System.out.println(ANSI_YELLOW+
                        "Email entered is invalid!"+
                        ANSI_RESET);
        }

        while (!passWordEnteredIsCorrect) {
            System.out.println("Please enter your current password: (or press 1 to exit)");
            passwordEntered = in.nextLine();
            if (passwordEntered.equals("1")) {
                return false;
            }
            if (passwordEntered.equals(userObj.getPassword())) {
                passWordEnteredIsCorrect = true;
            } else {
                System.out.println(ANSI_YELLOW+
                        "Password is incorrect !"+
                        ANSI_RESET);
            }

        }
        System.out.println("Please enter new password: (or press 1 to exit)");
        String newPasswordEntered = in.nextLine();
        if (newPasswordEntered.equals("1")) {
            return false;
        } else {
            return UserDAOImpl.getInstance().updatePassword(userObj.getUserId(), newPasswordEntered);
        }

    }

}
