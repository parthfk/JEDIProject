package com.flipkart.dao;


import com.flipkart.bean.User;
import com.flipkart.exception.UserNotFoundException;

import java.util.List;

public interface UserDAO {
    /**
     * Method which verifies credentials from database
     * @param userId
     * @param password
     * @return true if credentials are correct
     * @throws UserNotFoundException
     */
    public boolean verifyCredentials(String userId,String password) throws UserNotFoundException;

    /**
     * Method to get the role from database using SQL
     * @param userId
     * @return string
     */
    public String getRole(String userId);

    /**
     * Method to update Password in database
     * @param userId
     * @param newPassword
     * @return
     */
    public boolean updatePassword(String userId,String newPassword);


    /*
    Method to get list of all users
    @return
     */
    public List<User> getAllUsers();

}
