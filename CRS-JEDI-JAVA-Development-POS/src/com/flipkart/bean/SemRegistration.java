package com.flipkart.bean;

import com.flipkart.dao.StudentDAOImpl;

import java.util.ArrayList;


/**
 * SemRegistration Class- to store details about the semester registration for a student
 * Contains variables and their getter setter functions
 */
public class SemRegistration {
    private String studentId;
    private ArrayList<Course> primaryCourses;
    private ArrayList<Course> secondaryCourses;
    private Boolean regDone;
    private String semesterId;

    /**
     * constructor
     * @param student
     */
    public SemRegistration(Student student) {
        this.studentId = student.getUserId();
        this.primaryCourses = new ArrayList<>();
        this.secondaryCourses = new ArrayList<>();
        this.regDone = false;
    }
    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public ArrayList<Course> getPrimaryCourses() {
        return primaryCourses;
    }

    public void setPrimaryCourses(ArrayList<Course> primaryCourses) {
        this.primaryCourses = primaryCourses;
    }

    public ArrayList<Course> getSecondaryCourses() {
        return secondaryCourses;
    }

    public void setSecondaryCourses(ArrayList<Course> secondaryCourses) {
        this.secondaryCourses = secondaryCourses;
    }

    public Boolean getRegDone() {
        return regDone;
    }

    public void setRegDone(Boolean regDone) {
        this.regDone = regDone;
    }
}
