package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;

import java.util.ArrayList;

public interface StudentDAO {
    public void signup();
    public void selectPrimaryCourse(ArrayList<Course> primaryCourses);
    public ArrayList<String> viewPrimaryCourses();
    public void selectSecondaryCourse();
    public void viewSecondaryCourses();
    public void addCourse();
    public void dropCourse();
    public void payFee();
    public void displayRegisteredCourses();
    public GradeCard displayGradeCard ();
}
