package com.flipkart.bean;


/**
 * User Class- to store user related attributes
 * Contains variables and their getter setter functions
 */
public class User {
    private String userId;
    private String name;
    private String password;
    private String email;

    public User(){


    }

    /**
     * constructor
     * @param name
     * @param password
     * @param email
     * @param userType
     */
    public User(String name, String password, String email, String userType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    private String userType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
