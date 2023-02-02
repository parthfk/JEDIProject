package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.data.CourseData;
import com.flipkart.data.UserData;

import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class UserServiceOperation implements UserService {

    public User logIn() {
        Scanner in = new Scanner(System.in);
        boolean emailValidated = false,passWordEnteredIsCorrect = false;
        String inputEmail = null, passwordEntered=null;
        User userObj = null;

        //List<User> userData = null;
        System.out.println("Please enter your Role: ");
        String role = in.nextLine().toLowerCase();

        while(!emailValidated) {
            System.out.println("Please enter your emailId: (or press 1 to exit)");
            inputEmail = in.nextLine();
            if(inputEmail.equals("1")) {
                return null;
            }

            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(inputEmail);
            if (matcher.matches()){
                if(role.equals("admin"))
                {
                    List<Admin> adminList = UserData.adminList;
                    for(Admin u : adminList){
                        if(u.getEmail().equals(inputEmail)){
                            userObj = u;
                            emailValidated = true;
                            break;
                        }
                    }
                }
                else if(role.equals("student"))
                {
                    List<Student> studentList = UserData.studentList;
                    for(Student u : studentList){
                        if(u.getEmail().equals(inputEmail)){
                            userObj = u;
                            emailValidated = true;
                            break;
                        }
                    }
                }
                else if(role.equals("professor"))
                {
                    List<Professor> professorList = UserData.professorList;
                    for(Professor u : professorList){
                        if(u.getEmail().equals(inputEmail)){
                            userObj = u;
                            emailValidated = true;
                            break;
                        }
                    }
                }

            }
            if(!emailValidated)
                System.out.println("Email entered is incorrect !");
        }
        while(!passWordEnteredIsCorrect){
            System.out.println("Please enter your password: (or press 1 to exit)");
            passwordEntered = in.nextLine();
            if(passwordEntered.equals("1")) {
                return null;
            }
            if(passwordEntered.equals(userObj.getPassword())){
                passWordEnteredIsCorrect=true;
            }else{
                System.out.println("Password is incorrect !");
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
        List<Course> courses = CourseData.courseList;
        for(int i=0;i<courses.size();i++){
            System.out.println("CourseID: " + courses.get(i).getCourseID() + " Course Name: " + courses.get(i).getName()+
                    " Professor: "+courses.get(i).getProfessorID());
        }
        return CourseData.courseList;
    }

    public boolean updatePassword(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter your Role: ");
        String role = in.nextLine().toLowerCase();

        System.out.println("Enter your Email Id: ");


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
            if (matcher.matches()){
                if(role.equals("admin"))
                {
                    List<Admin> adminList = UserData.adminList;
                    for(User u : adminList){
                        if(u.getEmail().equals(inputEmail)){
                            userObj = u;
                            emailValidated = true;
                            break;
                        }
                    }
                }
                else if(role.equals("student"))
                {
                    List<Student> studentList = UserData.studentList;
                    for(User u : studentList){
                        if(u.getEmail().equals(inputEmail)){
                            userObj = u;
                            emailValidated = true;
                            break;
                        }
                    }
                }
                else if(role.equals("professor"))
                {
                    List<Professor> professorList = UserData.professorList;
                    for(User u : professorList){
                        if(u.getEmail().equals(inputEmail)){
                            userObj = u;
                            emailValidated = true;
                            break;
                        }
                    }
                }

            }
            if(!emailValidated)
                System.out.println("Email entered is incorrect !");
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
            userObj.setPassword(newPasswordEntered);
            return true;
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
