package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.data.CourseData;
import com.flipkart.data.UserData;
import com.flipkart.bean.User;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class UserServiceOperation implements UserService {
    public boolean logIn() {
        Scanner in = new Scanner(System.in);
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
                List<User> userData = UserData.userList;
                for(User u : userData){
                    if(u.getEmail().equals(inputEmail)){
                        userObj = u;
                        emailValidated = true;
                    }
                }
            }else{
                System.out.println("Email doesn't exist !");
            }
        }
        while(!passWordEnteredIsCorrect){
            System.out.println("Please enter your password: (or press 1 to exit)");
            passwordEntered = in.nextLine();
            inputEmail = in.nextLine();
            if(passwordEntered.equals("1")) {
                return false;
            }
            if(passwordEntered.equals(userObj.getPassword())){
                passWordEnteredIsCorrect=true;
            }else{
                System.out.println("Password is incorrect !");
            }

        }
        System.out.println("You have logged in successfully !");
        return true;
    }

    public boolean logOut() {
        System.out.println("You have logged out successfully !");
        return true;
    }

    public List<Course> viewCourseCatalogue() {
        System.out.println("These are the courses currently available: ");
        List<Course> courses = CourseData.courseList;
        for(int i=0;i<courses.size();i++){
            System.out.println("CourseID:" + courses.get(i).getCourseID() + "Course Name :" + courses.get(i).getName()+
                    " Professor :"+courses.get(i).getProfessorID());
        }
        return CourseData.courseList;
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
