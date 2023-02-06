package com.flipkart.dao;


import com.flipkart.exception.UserNotFoundException;

public interface UserDAO {
    public boolean verifyCredentials(String userId,String password) throws UserNotFoundException;
    public String getRole(String userId);
    public boolean updatePassword(String userId,String newPassword);

}
