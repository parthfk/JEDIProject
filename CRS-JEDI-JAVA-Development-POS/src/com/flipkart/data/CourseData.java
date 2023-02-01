package com.flipkart.data;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public class CourseData {
    static public ArrayList<Course> courseList;
    static {
        courseList = new ArrayList<>();
        courseList.add(new Course("122", "jhsjhw", "rjbfib", 5));
        courseList.add(new Course("123", "jhsjfjweb", "hjfbb", 5));
        courseList.add(new Course("124", "jhdfbehw", "rsejfbib", 5));
        courseList.add(new Course("125", "jfjbhjhw", "rjbsjhdbib", 5));
        courseList.add(new Course("126", "jhjdbsjhw", "rfib", 5));
        courseList.add(new Course("127", "qkduujhw", "rjbcjehbfib", 5));
    }
    public static void removeCourse(String courseId) {
        Course toBeRemoved = null;
        for (int i=0; i<courseList.size(); i++) {
            if (courseList.get(i).getCourseID().equals(courseId)) {
                toBeRemoved = courseList.get(i);
                break;
            }
        }
        if (toBeRemoved == null) {
            System.out.println("No course with this ID");
        } else {
            System.out.println("Bye");
        }
    }
}
