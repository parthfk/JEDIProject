package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;

import java.util.List;

public interface UserService {

    public User logIn();

    public boolean logOut(User user);

    public List<Course> viewCourseCatalogue();

    public boolean updatePassword();

}
