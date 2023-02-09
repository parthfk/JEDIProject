package com.flipkart.constant;

/**
 * Contains SQL Queries
 */

public class SQLConstants {

    //-----------------------------------USER SQL QUERIES-----------------------------------

    public static final String USER_ENTRY_QUERY = "INSERT into User values (?,?,?,?,?)";
    public static final String COUNT_USERS_QUERY = "SELECT COUNT(*) FROM User";
    public static final String INSERT_USER_QUERY =  "insert into User values(?,?,?,?,?)";
    public static final String UPDATE_PASSWORD_QUERY = "update user set password=? where email = ? ";
    public static final String COUNT_USERS_WITH_SPECIFIC_ROLE_QUERY = "SELECT COUNT(*) FROM User WHERE roleId=?";
    public static final String VERIFY_CREDENTIALS_QUERY = "select password from User from userId = ?";
    public static final String FETCH_USER_WITH_EMAIL_ID ="SELECT * FROM User where email= ?";


    //-----------------------------------ADMIN SQL QUERIES-----------------------------------

    public static final String INSERT_ADMIN_QUERY = "insert into Admin values(?,?,?,?)";


    //-----------------------------------PROFESSOR SQL QUERIES-----------------------------------

    public static final String INSERT_PROFESSOR_QUERY = "insert into Professor values(?,?,?,?,?)";


    //-----------------------------------STUDENT SQL QUERIES-----------------------------------

    public static final String UPDATE_STUDENT_APPROVAL_STATUS_QUERY = "UPDATE Student SET statusApproval=1 WHERE studentId = ?";
    public static final String SELECT_UNAPPROVED_STUDENTS_QUERY = "SELECT * FROM Student JOIN User ON Student.studentId = User.userId WHERE Student.statusApproval=0";
    public static final String UPDATE_STUDENT_GRADE_STATUS_QUERY = "UPDATE Student set gradeCardApproved = 1, gradeCardId = ? WHERE studentId = ?";
    public static final String STUDENT_ENTRY_QUERY = "INSERT into Student values (?,?,?,?,?,?,?,?,?)";
    public static final String GRADECARD_GENERATED = "SELECT gradeCardApproved from STUDENT where studentId = ?";

    //-----------------------------------OTHER SQL QUERIES-----------------------------------

    public static final String FETCH_STUDENT_FOR_GRADECARD_GENERATION_QUERY = "select name, studentId, email, departmentId from Student JOIN User on userId = studentId where gradeCardApproved = 0 and statusApproval = 1;";
    public static final String FETCH_GRADES_QUERY = "select grade, semesterId from RegisteredCourse where studentId = ?";
    public static final String COUNT_GRADECARDS_QUERY = "select count(*) from GradeCard";
    public static final String INSERT_GRADECARD_QUERY = "insert into GradeCard (gradeCardId,studentId,SGPA,semesterId) values(?,?,?,?)";
    public static final String INSERT_CATALOGUE_QUERY = "insert into Catalogue values (?,?,?,?)";
    public static final String FETCH_CATALOGUE_QUERY = "select Catalogue.courseId, Course.name, Catalogue.professorId, Catalogue.availableSeats from Catalogue, Course where Catalogue.courseId = Course.courseId and professorId is not null";
    public static final String FETCH_CATALOGUE_QUERY_ALL = "select Catalogue.courseId, Course.name, Catalogue.professorId, Catalogue.availableSeats from Catalogue, Course where Catalogue.courseId = Course.courseId";
    public static final String DELETE_FROM_CATALOGUE_QUERY = "delete from Catalogue where courseId = ?";
    public static final String CHECK_IF_COURSE_EXISTS_QUERY = "SELECT COUNT(*) FROM Course WHERE courseId = ?";
    public static final String INSERT_IN_COURSE_QUERY = "INSERT INTO Course VALUES (?, ?)";
    public static final String PAYMENT_ENTRY_QUERY = "INSERT into Payment values (?,?,?,?)";

    public static final String UPDATE_COURSE_QUERY = "UPDATE Catalogue SET professorId=? WHERE courseId=?";
    public static final String UPDATE_REGISTERED_COURSE_QUERY = "UPDATE RegisteredCourse SET grade=? WHERE studentId=? AND courseId= ? AND semesterId=?";
    public static final String NEW_USER_ENTRY_QUERY =
            "INSERT into SemRegistration values(?,?,?,?,?,?,?,?,?) " +
                    "on duplicate key UPDATE pc1=?, pc2=?, pc3=?, pc4=?";

    public static final   String NEW_USER_ENTRY_QUERY_2 =
            "INSERT into SemRegistration values(?,?,?,?,?,?,?,?,?) " +
                    "on duplicate key UPDATE sc1=?, sc2=?";

    public static final String UPDATE_USER_ENTRY_QUERY =
            "UPDATE SemRegistration SET regDone=1 WHERE studentId=? ";

    public static final String INSERT_REGISTERED_COURSE_QUERY= "INSERT into RegisteredCourse values(?,?)";

    public static final String UPDATE_CATALOGUE_QUERY = "UPDATE Catalogue set availableSeats=availableSeats-1 where courseId=?";

    public static final String DELETE_REGISTERED_COURSE_QUERY = "DELETE from RegisteredCourse where courseId=? AND studentId=?";

    public static final String UPDATE_CATALOGUE_QUERY_2 = "UPDATE Catalogue set availableSeats=availableSeats+1 where  courseId=?";

    public static final String INSERT_IN_REGISTERED_COURSE =
            "INSERT into RegisteredCourse values (?,?,?,?)";



    public static final String updateCourseQuery = "UPDATE Catalogue SET professorId=? WHERE courseId=?";
    public static final String updateRegisteredCourse = "UPDATE RegisteredCourse SET grade=? WHERE studentId=? AND courseId= ? AND semesterId=?";

    public static final String GET_ALL_USERS = "SELECT * from User";

}
