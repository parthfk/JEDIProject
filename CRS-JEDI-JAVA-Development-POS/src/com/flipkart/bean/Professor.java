package com.flipkart.bean;

import java.util.List;

public class Professor extends User{
    private String DepartmentID;
    private List<Course> courseTaken;
    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public List<Course> getCourseTaken() {
        return courseTaken;
    }

    public void setCourseTaken(List<Course> courseTaken) {
        this.courseTaken = courseTaken;
    }
}
