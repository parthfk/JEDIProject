package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface AdminService {
    public boolean addCourse();

    public boolean removeCourse();

    public void approveStudent();
    public void addProfessor();

    public boolean addAdmin();

    public void generateGradeCard();

}
