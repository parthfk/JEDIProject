package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.List;

public interface UserService {

    public boolean logIn();

    public boolean logOut();

    public List<Course> viewCourseCatalogue();

}
