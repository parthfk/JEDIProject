package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.CatalogueDAOImpl;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.data.CourseData;
import com.flipkart.data.UserData;
import com.flipkart.exception.*;
import com.flipkart.utils.Utils;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class UserServiceOperation implements UserService {

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
                                System.out.println(u.getEmail());
                                System.out.println(u.isStatusApproval());
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
                        System.out.println("Please enter a Valid role, which can be 'student', 'professor' or 'admin'!");
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Failure in reading the db : \n" + e.getMessage());
                }
            } else {
                System.out.println("Invalid formatted email!");
                return null;
            }
        }

        while (!passWordEnteredIsCorrect) {
            if (passwordEntered.equals(userObj.getPassword())) {
                passWordEnteredIsCorrect = true;
            } else {
                System.out.println("Password is incorrect !");
                return null;
            }
        }
        System.out.println("You have logged in successfully !");
        return userObj;
    }

    private void checkUserExists(String inputEmail) {
        List<User> userList = Utils.getUserList();
        for (User u : userList) {
            if (u.getEmail().matches(inputEmail)) {
                System.out.println("You have entered the wrong role. Please try again, entering the appropriate role");
                System.out.println("Your role is " + u.getUserType());
                return;
            }
        }
        System.out.println("No user exists with this email! Please contact admin for help!");
    }

    public boolean logOut(User user) {
        System.out.println("You have logged out successfully !");
        return true;
    }

    public List<Course> viewCourseCatalogue() {
        List<Course> courses = new CatalogueDAOImpl().fetchCatalogue(false);

        if(courses.size()==0)
        {
            System.out.println("No Courses exist in Catalogue");
            return courses;


        }
        System.out.println("These are the courses currently available: ");
//        for(int i=0;i<courses.size();i++) {
//            System.out.println("CourseID \t Course Name \t Professor ID ");
//            System.out.println(courses.get(i).getCourseID() + "\t\t\t " + courses.get(i).getName() + "\t \t \t\t  " + courses.get(i).getProfessorID());
//        }
            StringBuffer buffer = new StringBuffer();
            Formatter fmt = new Formatter();

            fmt.format("\n%14s %14s %14s\n", "Course ID", "Course Name", "Professor");

            for(Course c: courses)
            {
                fmt.format("%14s %14s %14s\n", c.getCourseID(),c.getName(),c.getProfessorID());
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
                System.out.println("Email entered is invalid!");
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
                System.out.println("Password is incorrect !");
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

//    public static boolean shouldExit(String string) {
//        int intValue;
//        if(string == null || string.equals("")) {
//            System.out.println("String cannot be parsed, it is null or empty.");
//            return false;
//        }
//        try {
//            intValue = Integer.parseInt(string);
//            return intValue == 1;
//        }
//        catch (NumberFormatException e) {
//        }
//        return false;
//    }
}
