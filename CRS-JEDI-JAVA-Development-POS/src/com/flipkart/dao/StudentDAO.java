package com.flipkart.dao;

import com.flipkart.bean.GradeCard;

public interface StudentDAO {
    public void signup();
    public void selectPrimaryCourse();
    public void selectSecondaryCourse();
    public void addCourse();
    public void dropCourse();
    public void payFee();
    public void displayRegisteredCourses();
    public GradeCard displayGradeCard ();
}
