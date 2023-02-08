package com.flipkart.constant;

public class SQLConstants {

    public static final String USER_ENTRY_QUERY = "INSERT into User values (?,?,?,?,?)";
    public static final String PAYMENT_ENTRY_QUERY = "INSERT into Payment values (?,?,?,?)";

    public static final String updateCourseQuery = "UPDATE Catalogue SET professorId=? WHERE courseId=?";

    public static final String updateRegisteredCourse = "UPDATE RegisteredCourse SET grade=? WHERE studentId=? AND courseId= ? AND semesterId=?";
}
