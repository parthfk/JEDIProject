package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
import com.flipkart.exception.AdminNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * Method to log in a user after verifying credentials and identifying role
     * @return User object
     */
    public User logIn(String a,String b,String c);

    /**
     * Method to logout the user from the session
     * @param user object
     * @return boolean if successfully logged out.
     */
    public boolean logOut(User user);

    /**
     * Method to see the list of courses offered in the catalogue. This is kept in user class as course catalogue can be viewed by every type of user.
     * @return List of courses being offered
     */
    List<Course> viewCourseCatalogue(boolean viewAll) throws SQLException;
    /**
     * Method to update password of a user
     * @return boolean indicating if the password is updated successfully
     */
    boolean updatePassword();


    /**
     *  Method to get list of all users
     */
    List<User> getAllUsers();
}
