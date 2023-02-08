package com.flipkart.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Professor Class- to store professor related attributes
 * Contains variables and their getter setter functions
 */

public class Professor extends User {
    private Date dob;
    private String address;
    private String mobileNumber;
    private String DepartmentID;
    private List<Course> coursesTaken;

    public Professor(){
        DepartmentID="";
        coursesTaken = new ArrayList<Course>();
    }
    @Override
    public String toString() {
        return "Professor{" +
                "ProfId='" + this.getUserId() + '\'' +
                ", Name =" + this.getName() +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", dob=" + dob +
                '}';
    }
    public Professor(String name, String email, String password,String departmentId,
                 String address, String mobileNumber, Date dob){
        super(name,password,email,"professor");
        this.DepartmentID = departmentId;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        coursesTaken = new ArrayList<>();
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public String getDepartmentID() {
        return DepartmentID;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
