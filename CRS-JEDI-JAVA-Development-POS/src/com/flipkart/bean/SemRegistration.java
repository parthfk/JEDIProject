package com.flipkart.bean;

import java.util.ArrayList;

public class SemRegistration {
    private String studentId;
    private ArrayList<Course> primaryCourses;
    private ArrayList<Course> secondaryCourses;
    private Boolean regDone;

    public SemRegistration(Student student) {
        this.studentId = student.getUserId();
        this.primaryCourses = new ArrayList<>();
        this.secondaryCourses = new ArrayList<>();
        this.regDone = false;
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
