package com.flipkart.data;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {
   // public static List<User> userList = new ArrayList<User>();

    public static List<Student> studentList = new ArrayList<Student>();
    public static List<Professor> professorList = new ArrayList<Professor>();
    public static List<Admin> adminList = new ArrayList<Admin>();




    static {
        for (int i = 0; i < 10; i++) {
//            User u = new User();
//            u.setUserId(String.valueOf(i));
//            u.setEmail(i + "@gmail.com");
//            u.setName(i + "jagaa");
//            u.setPassword(i + "ysfysiuf");
            if (i % 3 == 0) {
                Admin admin = new Admin();
                admin.setUserId(String.valueOf(i));
                admin.setEmail(i + "@gmail.com");
                admin.setName(i + "jagaa");
                admin.setPassword(i + "ysfysiuf");
                admin.setUserType("Admin");
                adminList.add(admin);

            } else if (i % 3 == 1) {
                Professor professor = new Professor();
                professor.setUserId(String.valueOf(i));
                professor.setEmail(i + "@gmail.com");
                professor.setName(i + "jagaa");
                professor.setPassword(i + "ysfysiuf");
                professor.setUserType("Professor");
                professorList.add(professor);
            } else {
                Student student = new Student();
                student.setUserId(String.valueOf(i));
                student.setEmail(i + "@gmail.com");
                student.setName(i + "jagaa");
                student.setPassword(i + "ysfysiuf");
                student.setUserType("Professor");
                studentList.add(student);
            }
        }
    }

}
