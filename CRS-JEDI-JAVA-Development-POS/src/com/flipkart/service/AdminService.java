package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface AdminService {
    public boolean addCourse();
    public void deleteCourse();

    public boolean removeCourse(int courseId);

    public void approveStudent(Student student);
    public boolean addProfessor(Professor professor);

    public boolean addAdmin(Admin admin);

    public void generateGradeCard(Student student);

}
