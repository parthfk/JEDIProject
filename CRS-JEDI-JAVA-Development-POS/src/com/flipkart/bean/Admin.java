package com.flipkart.bean;

import java.sql.Date;

public class Admin extends User {
    private Date dob;
    private String address;
    private String mobileNumber;

    public Date getDate(){return dob;}
}
