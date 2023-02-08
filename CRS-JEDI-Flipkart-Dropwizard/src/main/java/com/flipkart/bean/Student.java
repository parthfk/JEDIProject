package com.flipkart.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Student Class- to store student related attributes
 * Contains variables and their getter setter functions
 */

public class Student extends User {
    private String departmentID;
    private boolean feeDone;
    private boolean statusApproval;
    private SemRegistration semRegistration;
    private List<Course> courseRegistered;
    private boolean gradeCardApproved;
    private GradeCard gradeCard;
    private String address;

    @Override
    public String toString() {
        return "Student{" +
                "departmentID='" + departmentID + '\'' +
                ", feeDone=" + feeDone +
                ", statusApproval=" + statusApproval +
                ", semRegistration=" + semRegistration +
                ", courseRegistered=" + courseRegistered +
                ", gradeCardApproved=" + gradeCardApproved +
                ", gradeCard=" + gradeCard +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", dob=" + dob +
                '}';
    }

    private String mobileNumber;
    private Date dob;

    public Student(String name, String email, String password, String departmentID,
                   String address, String mobileNumber, Date dob){
        super(name,password,email,"student");
        this.departmentID=departmentID;
        this.feeDone=false;
        this.statusApproval=false;
        this.courseRegistered=new ArrayList<>();
        this.gradeCardApproved=false;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
    }

    public boolean isGradeCardApproved() {
        return gradeCardApproved;
    }

    public void setGradeCardApproved(boolean gradeCardApproved) {
        this.gradeCardApproved = gradeCardApproved;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String department_ID) {
        departmentID = department_ID;
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

    public SemRegistration getSemRegistration() {
        return semRegistration;
    }

    public void setSemRegistration(SemRegistration semRegistration) {
        this.semRegistration = semRegistration;
    }

    public List<Course> getCourseRegistered() {
        return courseRegistered;
    }

    public void setCourseRegistered(List<Course> courseRegistered) {
        this.courseRegistered = courseRegistered;
    }

    public GradeCard getGradeCard() {
        return gradeCard;
    }

    public void setGradeCard(GradeCard gradeCard) {
        this.gradeCard = gradeCard;
    }

    public String getAddress() {return this.address;}

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
