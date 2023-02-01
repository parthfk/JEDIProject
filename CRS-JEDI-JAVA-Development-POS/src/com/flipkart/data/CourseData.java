package com.flipkart.data;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public class CourseData {
    public ArrayList<Course> courseList;

    public CourseData() {
        courseList = new ArrayList<>();
        courseList.add(new Course("122", "jhsjhw", "rjbfib", 5));
        courseList.add(new Course("123", "jhsjfjweb", "hjfbb", 5));
        courseList.add(new Course("124", "jhdfbehw", "rsejfbib", 5));
        courseList.add(new Course("125", "jfjbhjhw", "rjbsjhdbib", 5));
        courseList.add(new Course("126", "jhjdbsjhw", "rfib", 5));
        courseList.add(new Course("127", "qkduujhw", "rjbcjehbfib", 5));
    }
}
