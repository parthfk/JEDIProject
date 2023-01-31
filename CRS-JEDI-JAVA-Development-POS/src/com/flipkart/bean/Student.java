package com.flipkart.bean;

import java.util.List;

public class Student extends User {
    private String DepartmentID;
    private boolean feeDone;
    private boolean statusApproval;
    private SemRegistrationServiceOperation semRegistration;
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

    public SemRegistrationServiceOperation getSemRegistration() {
        return semRegistration;
    }

    public void setSemRegistration(SemRegistrationServiceOperation semRegistration) {
        this.semRegistration = semRegistration;
    }

    public List<Course> getCourseRegistered() {
        return courseRegistered;
    }

    public void setCourseRegistered(List<Course> courseRegistered) {
        this.courseRegistered = courseRegistered;
    }
}
