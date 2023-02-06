package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDAO {
    /**
     * @param course
     */
    void selectCourseDAO(Course course);

    /**
     * @param courseId
     * @param semesterId
     * @return
     */
    List<Student> viewEnrolledStudentListDao(String courseId, String semesterId);

    /**
     * @param id
     * @return
     */
    List<Course> viewCourseListDao(String id);

    /**
     * @param studentId
     * @param semId
     * @param courseId
     * @param grade
     */
    void addGrade(String studentId,String semId,String courseId,String grade);
}
