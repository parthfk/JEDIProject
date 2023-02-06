package com.flipkart.dao;


import com.flipkart.exception.UserNotFoundException;

public interface UserDAO {
    /**
     * @param userId
     * @param password
     * @return
     * @throws UserNotFoundException
     */
    public boolean verifyCredentials(String userId,String password) throws UserNotFoundException;

    /**
     * @param userId
     * @return
     */
    public String getRole(String userId);

    /**
     * @param userId
     * @param newPassword
     * @return
     */
    public boolean updatePassword(String userId,String newPassword);

}
