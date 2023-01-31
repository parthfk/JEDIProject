package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.List;

public interface CatalogueService {
    public List<Course> viewCourseList ();
    public void addCourse();
    public void deleteCourse();
}
