package com.flipkart.dao;


import com.flipkart.bean.User;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    /**
     * Method which verifies credentials from database
     * @param userId
     * @param password
     * @return true if credentials are correct
     * @throws UserNotFoundException
     */
    public boolean verifyCredentials(String userId,String password) throws UserNotFoundException, PasswordMismatchException,SQLException;

    /**
     * Method to get the role from database using SQL
     * @param userId
     * @return string
     */
    public String getRole(String userId) throws SQLException;

    /**
     * Method to update Password in database
     * @param inputEmail
     * @param newPassword
     * @return
     */
    public boolean updatePassword(String inputEmail,String newPassword) throws SQLException;


    /*
    Method to get list of all users
    @return
     */
    public List<User> getAllUsers() throws SQLException;

}
