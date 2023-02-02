package com.flipkart.data;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    public static List<Student> studentList = new ArrayList<Student>();
    public static List<Professor> professorList = new ArrayList<Professor>();
    public static List<Admin> adminList = new ArrayList<Admin>();

    static {
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                Admin admin = new Admin();
                admin.setUserId(String.valueOf(i));
                admin.setEmail(i + "@gmail.com");
                admin.setName(i + "jagaa");
                admin.setPassword(i + "abc");
                admin.setUserType("Admin");
                adminList.add(admin);

            } else if (i % 3 == 1) {
                Professor professor = new Professor();
                professor.setUserId(String.valueOf(i));
                professor.setEmail(i + "@gmail.com");
                professor.setName(i + "jagaa");
                professor.setPassword(i + "abc");
                professor.setUserType("Professor");
                professorList.add(professor);
            } else {
                Student student = new Student(i + "jagaa",i + "@gmail.com",i + "abc","qwertyui");
                student.setUserId(String.valueOf(i));
                student.setUserType("Student");
                if (i == 2) student.setStatusApproval(true);
                studentList.add(student);
            }
        }
    }

}
