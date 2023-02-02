package com.flipkart.data;

import com.flipkart.bean.RegisteredCourse;

import java.util.ArrayList;

public class RegisteredCourseData {
    static public ArrayList<RegisteredCourse> regCourseList;
    static {
        regCourseList = new ArrayList<>();
        regCourseList.add(new RegisteredCourse("122", "2", "1"));
        regCourseList.add(new RegisteredCourse("123", "2", "1"));
        regCourseList.add(new RegisteredCourse("124", "5", "1"));
        regCourseList.add(new RegisteredCourse("125", "5", "1"));
        regCourseList.add(new RegisteredCourse("125", "8", "1"));
        regCourseList.add(new RegisteredCourse("126", "8", "1"));
        regCourseList.add(new RegisteredCourse("126", "9", "1"));
        regCourseList.add(new RegisteredCourse("126", "10", "1"));
    }
//    public static void removeCourse(String courseId) {
//        Course toBeRemoved = null;
//        for (int i=0; i<courseList.size(); i++) {
//            if (courseList.get(i).getCourseID().equals(courseId)) {
//                toBeRemoved = courseList.get(i);
//                break;
//            }
//        }
//        if (toBeRemoved == null) {
//            System.out.println("No course with this ID");
//        } else {
//            System.out.println("Bye");
//        }
//    }
}
