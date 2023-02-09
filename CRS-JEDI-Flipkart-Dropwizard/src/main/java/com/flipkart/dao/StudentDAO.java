package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;

import java.util.ArrayList;

public interface StudentDAO {
    /**
     * Creates a new User and Student record in the Database
     */
    public void signup(Student newStudent);

    /**
     * Given the list of primary courses, this sets SemRegistration record of the
     * student with the given list (overwrites it)
     * @param primaryCourses
     */
    public void selectPrimaryCourse(String studentId, ArrayList<String> primaryCourses);

    /**
     * @return an array list of CourseIds, the current student's selected primary courses
     */
    public ArrayList<String> viewPrimaryCourses(String studentId);

    /**
     * Given the list of primary courses, this sets SemRegistration record of the
     * student with the given list (overwrites it)
     * @param secondaryCourses
     */
    public void selectSecondaryCourse(String studentId, ArrayList<String> secondaryCourses);

    /**
     * @return an array list of CourseIds, the current student's selected primary courses
     */
    public ArrayList<String> viewSecondaryCourses(String studentId);

    /**
     * Confirms the student's registration using their selected Primary and Secondary courses
     * This also sets their regDone value to true, showing that their registration for the
     * semester is completed
     */
    public void confirmRegistration(String studentId);

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
    public ArrayList<String> displayRegisteredCourses(String studentId);

    /**
     * @return the student's gradecard, as a GradeCard object
     */
    public String displayGradeCard (String studentId);

    /**
     * Sets a new record in the RegisteredCourse table, using the given list below
     * @param registeredCourses
     */
    public void setRegisteredCourses(String studentId, ArrayList<Course> registeredCourses);
}
