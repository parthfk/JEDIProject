package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorService {
    public void addGrade();
    public List<Student> viewEnrolledStudentList();
    public void selectCourse(Course course);
    public List<Course> viewCourseList();
}
