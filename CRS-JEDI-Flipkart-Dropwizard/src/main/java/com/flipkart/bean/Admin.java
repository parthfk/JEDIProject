package com.flipkart.bean;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Admin Class- to store admin related attributes
 * Contains variables and their getter setter functions
 */

public class Admin extends User {

    private Date dob;
    private String address;
    private String mobileNumber;

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

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "AdminId='" + this.getUserId() + '\'' +
                ", Name =" + this.getName() +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", dob=" + dob +
                '}';
    }
    public Admin(){}
    public Admin(String name, String email, String password,
                   String address, String mobileNumber, Date dob){
        super(name,password,email,"admin");
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
    }


}
