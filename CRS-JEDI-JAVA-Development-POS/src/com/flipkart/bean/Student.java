package com.flipkart.bean;

import com.flipkart.service.SemRegistrationService;

import java.util.List;

public class Student extends User {
    private String DepartmentID;
    private boolean feeDone;
    private boolean statusApproval;
    private SemRegistrationService semRegistration;
    private List<Course> courseRegistered;

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public boolean isFeeDone() {
        return feeDone;
    }

    public void setFeeDone(boolean feeDone) {
        this.feeDone = feeDone;
    }

    public boolean isStatusApproval() {
        return statusApproval;
    }

    public void setStatusApproval(boolean statusApproval) {
        this.statusApproval = statusApproval;
    }

    public SemRegistrationService getSemRegistration() {
        return semRegistration;
    }

    public void setSemRegistration(SemRegistrationService semRegistration) {
        this.semRegistration = semRegistration;
    }

    public List<Course> getCourseRegistered() {
        return courseRegistered;
    }

    public void setCourseRegistered(List<Course> courseRegistered) {
        this.courseRegistered = courseRegistered;
    }
}
