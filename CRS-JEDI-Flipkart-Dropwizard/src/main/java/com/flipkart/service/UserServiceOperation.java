package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.CatalogueDAOImpl;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.*;
import com.flipkart.utils.Utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.*;

public class UserServiceOperation implements UserService {

    public User logIn(String inEmail, String passEntered,String role) throws AdminNotFoundException, StudentNotApprovedException, ProfessorNotFoundException, StudentNotFoundException,RoleMismatchException {
        boolean emailValidated = false, passWordEnteredIsCorrect = false;
        User userObj = null;

        String inputEmail = inEmail.toLowerCase();
        String passwordEntered = passEntered.toLowerCase();
         role = role.toLowerCase();
        while (!emailValidated) {
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(inputEmail);

            if (matcher.matches()) {

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
                            boolean res = checkUserExists(inputEmail);
                            if(res){
                                throw new RoleMismatchException(role);
                            }else{
                                throw new AdminNotFoundException(inputEmail);
                            }
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
                                        throw new StudentNotApprovedException(inputEmail);
                                }
                                emailValidated = true;
                                break;
                            }
                        }
                        if (!emailValidated) {
                            boolean res = checkUserExists(inputEmail);
                            if(res){
                                throw new RoleMismatchException(role);
                            }else{
                                throw new StudentNotFoundException(inputEmail);
                            }
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
                            boolean res = checkUserExists(inputEmail);
                            if(res){
                                throw new RoleMismatchException(role);
                            }else{
                                throw new ProfessorNotFoundException(inputEmail);
                            }
                        }
                    } else {
                        throw new RoleMismatchException(role);
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
        return userObj;
    }

    @Override
    public boolean logOut(User user) {
        return true;
    }

    public boolean checkUserExists(String inputEmail) {
        List<User> userList = Utils.getUserList();
        for (User u : userList) {
            if (u.getEmail().matches(inputEmail)) {
                System.out.println("You have entered the wrong role. Please try again, entering the appropriate role");
                System.out.println("Your role is " + u.getUserType());
                return false;
            }
        }
        return true;
    }

    public List<Course> viewCourseCatalogue(boolean viewAll) {
        List<Course> courses;
        try {
            courses = new CatalogueDAOImpl().fetchCatalogue(viewAll);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(courses.size()==0)
        {
            return courses;
        }
        return courses;

    }

    public boolean updatePassword(String inputEmail,String oldPassword,String newPassword,String role) throws PasswordMismatchException,UserNotFoundException,SQLException{


        UserDAO userDAOInstance = UserDAOImpl.getInstance();
        if(userDAOInstance.verifyCredentials(inputEmail,oldPassword)){
            System.out.println("verified");
                return userDAOInstance.updatePassword(inputEmail,newPassword);
        }
        System.out.println("failed");
        return false;
    }


    @Override
    public List<User> getAllUsers() throws SQLException {
        return UserDAOImpl.getInstance().getAllUsers();
    }
}
