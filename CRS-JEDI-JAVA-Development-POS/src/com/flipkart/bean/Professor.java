package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class Professor extends User {
    private String DepartmentID;
    private List<Course> coursesTaken;
    public String getDepartmentID() {
        return DepartmentID;
    }

    public Professor(){
        DepartmentID="";
        coursesTaken = new ArrayList<Course>();
    }
    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public List<Course> getCoursesTaken() {
        return coursesTaken;
    }

    public void setCoursesTaken(List<Course> coursesTaken) {
        this.coursesTaken = coursesTaken;
    }
}
