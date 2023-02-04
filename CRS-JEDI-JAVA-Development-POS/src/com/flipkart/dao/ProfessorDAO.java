package com.flipkart.dao;

import com.flipkart.bean.Course;

public interface ProfessorDAO {
    void selectCourseDAO(Course course);

    void viewEnrolledStudentListDao(String courseId,String semesterId);

}
