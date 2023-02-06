package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDAO {
    void selectCourseDAO(Course course);

    List<Student> viewEnrolledStudentListDao(String courseId, String semesterId);

    List<Course> viewCourseListDao(String id);

    void addGrade(String studentId,String semId,String courseId,String grade);
}
