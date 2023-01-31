package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.List;

public interface SemRegistrationService {
    public List<Course> showCourseList();
    public void selectPrimaryCourse();
    public void selectSecondaryCourse();
    public void addCourse();
    public void dropCourse();
    public void payFee();
}
