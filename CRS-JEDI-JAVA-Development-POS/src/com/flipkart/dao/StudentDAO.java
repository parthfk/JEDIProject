package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;

import java.util.ArrayList;

public interface StudentDAO {
    public void signup();
    public void selectPrimaryCourse(ArrayList<Course> primaryCourses);
    public ArrayList<String> viewPrimaryCourses();
    public void selectSecondaryCourse(ArrayList<Course> secondaryCourses);
    public ArrayList<String> viewSecondaryCourses();
    public void confirmRegistration();
    public void addCourse();
    public void dropCourse();
    public void payFee();
    public ArrayList<String> displayRegisteredCourses();
    public GradeCard displayGradeCard ();
    public void setRegisteredCourses(ArrayList<Course> registeredCourses);
}
