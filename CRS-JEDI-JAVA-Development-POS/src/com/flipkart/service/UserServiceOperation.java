package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.CatalogueDAOImpl;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.data.CourseData;
import com.flipkart.data.UserData;
import com.flipkart.utils.Utils;

import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class UserServiceOperation implements UserService {

    public User logIn() {
        Scanner in = new Scanner(System.in);
        boolean emailValidated = false,passWordEnteredIsCorrect = false;
        User userObj = null;

        //List<User> userData = null;
        System.out.println("Please enter your Email id: ");
        String inputEmail = in.nextLine().toLowerCase();
        System.out.println("Please enter your Password: ");
        String passwordEntered = in.nextLine().toLowerCase();
        System.out.println("Please enter your Role: ");
        String role = in.nextLine().toLowerCase();

        while(!emailValidated) {
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
                            System.out.println("Invalid Credentials. Please try again");
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
                                    return null;
                                }
                                emailValidated = true;
                                break;
                            }
                        }
                        if (!emailValidated) {
                            System.out.println("Invalid Credentials. Please try again");
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
                            System.out.println("Invalid Credentials. Please try again");
                            return null;
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("Failure in reading the db : \n" + e.getMessage());
                }
            }
                else {
                        System.out.println("Invalid formatted email!");
                        return null;
                    }
            }

        while(!passWordEnteredIsCorrect){
            if(passwordEntered.equals(userObj.getPassword())){
                passWordEnteredIsCorrect=true;
            }else{
                System.out.println("Password is incorrect !");
                return null;
            }
        }
        System.out.println("You have logged in successfully !");
        return userObj;
    }

    public boolean logOut(User user) {
        System.out.println("You have logged out successfully !");
        return true;
    }

    public List<Course> viewCourseCatalogue() {
        System.out.println("These are the courses currently available: ");
        List<Course> courses = new CatalogueDAOImpl().fetchCatalogue();
        for(int i=0;i<courses.size();i++){
            System.out.println("CourseID: " + courses.get(i).getCourseID() + " Course Name: " + courses.get(i).getName()+
                    " Professor: "+courses.get(i).getProfessorID());
        }
        return courses;
    }

    public boolean updatePassword(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter your Role: ");
        String role = in.nextLine().toLowerCase();

        boolean emailValidated = false,passWordEnteredIsCorrect = false;
        String inputEmail = null, passwordEntered=null;
        User userObj = null;
        while(!emailValidated) {
            System.out.println("Please enter your emailId: (or press 1 to exit)");
            inputEmail = in.nextLine();
            if(inputEmail.equals("1")) {
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
                    } else if (role.equals("student")) {
                        List<Student> studentList = Utils.getStudentList();
                        for (User u : studentList) {
                            if (u.getEmail().equals(inputEmail)) {
                                userObj = u;
                                emailValidated = true;
                                break;
                            }
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
                    }
                }catch(NullPointerException e){
                    System.out.println("Error in reading db :"+e.getMessage());
                }
            }
            if(!emailValidated)
                System.out.println("Email entered is invalid!");
        }

        while(!passWordEnteredIsCorrect){
            System.out.println("Please enter your current password: (or press 1 to exit)");
            passwordEntered = in.nextLine();
            if(passwordEntered.equals("1")) {
                return false;
            }
            if(passwordEntered.equals(userObj.getPassword())){
                passWordEnteredIsCorrect=true;
            }else{
                System.out.println("Password is incorrect !");
            }

        }
        System.out.println("Please enter new password: (or press 1 to exit)");
        String newPasswordEntered = in.nextLine();
        if(newPasswordEntered.equals("1")) {
            return false;
        }
        else
        {
             return UserDAOImpl.getInstance().updatePassword(userObj.getUserId(),newPasswordEntered);
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
