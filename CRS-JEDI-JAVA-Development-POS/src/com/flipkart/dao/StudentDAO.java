package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;

import java.util.ArrayList;

public interface StudentDAO {
    /**
     * Creates a new User and Student record in the Database
     */
    public void signup();

    /**
     * Given the list of primary courses, this sets SemRegistration record of the
     * student with the given list (overwrites it)
     * @param primaryCourses
     */
    public void selectPrimaryCourse(ArrayList<Course> primaryCourses);

    /**
     * @return an array list of CourseIds, the current student's selected primary courses
     */
    public ArrayList<String> viewPrimaryCourses();

    /**
     * Given the list of primary courses, this sets SemRegistration record of the
     * student with the given list (overwrites it)
     * @param secondaryCourses
     */
    public void selectSecondaryCourse(ArrayList<Course> secondaryCourses);

    /**
     * @return an array list of CourseIds, the current student's selected primary courses
     */
    public ArrayList<String> viewSecondaryCourses();

    /**
     * Confirms the student's registration using their selected Primary and Secondary courses
     * This also sets their regDone value to true, showing that their registration for the
     * semester is completed
     */
    public void confirmRegistration();

    /**
     * Adds a course in the add-drop window
     * @param courseId
     * @return success value
     */
    public boolean addCourse(String courseId);

    /**
     * drops a course in the add-drop window
     * @param courseId
     * @return success value
     */
    public boolean dropCourse(String courseId);

    /**
     * @return the student's registered courses.
     * If the student has not completed registration, this returns null.
     * And we show a message asking the user to complete registration
     */
    public ArrayList<String> displayRegisteredCourses();

    /**
     * function to display grade card
     */
    public void displayGradeCard ();

    /**
     * Sets a new record in the RegisteredCourse table, using the given list below
     * @param registeredCourses
     */
    public void setRegisteredCourses(ArrayList<Course> registeredCourses);
}
