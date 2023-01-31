package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public class AdminServiceOperation extends UserServiceOperation implements AdminService{
    public boolean addCourse() {
        return true;
    }
    public void deleteCourse(){

    }
    public boolean removeCourse(int courseId)  {
        return true;
    }

    public void approveStudent(Student student) {

    }
    public boolean addProfessor(Professor professor) {
        return true;
    }

    public boolean addAdmin(Admin admin) {
        return true;
    }

    public void generateGradeCard(Student student) {

    }
}
