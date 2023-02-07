package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;

import java.util.ArrayList;

public interface StudentDAO {
    /**
     * SS
     */
    public void signup();

    /**
     * @param primaryCourses SS
     */
    public void selectPrimaryCourse(ArrayList<Course> primaryCourses);

    /**
     * @return
     */
    public ArrayList<String> viewPrimaryCourses();

    /**
     * @param secondaryCourses
     */
    public void selectSecondaryCourse(ArrayList<Course> secondaryCourses);

    /**
     * @return
     */
    public ArrayList<String> viewSecondaryCourses();

    /**
     *
     */
    public void confirmRegistration();

    /**
     * @param courseId
     * @return
     */
    public boolean addCourse(String courseId);

    /**
     * @param courseId
     * @return
     */
    public boolean dropCourse(String courseId);

    /**
     * @return
     */
    public ArrayList<String> displayRegisteredCourses();

    /**
     * @return
     */
    public void displayGradeCard ();

    /**
     * @param registeredCourses
     */
    public void setRegisteredCourses(ArrayList<Course> registeredCourses);
}
