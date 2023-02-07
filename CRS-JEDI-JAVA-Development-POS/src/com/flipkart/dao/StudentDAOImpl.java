package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.DBConnection;
import com.flipkart.constant.RoleId;
import com.flipkart.constant.SQLConstants;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;

import static com.flipkart.constant.DBConnection.*;

public class StudentDAOImpl implements StudentDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private Student student;

    private StudentDAOImpl(Student student) {
        this.conn = DbConnection.getConnectionInstance();
    }

    // Singleton Pattern
    private static StudentDAOImpl dao = null;
    public static StudentDAOImpl getInstance(Student student) {
        if (dao != null) return dao;
        return dao = new StudentDAOImpl(student);
    }

    @Override
    public void signup() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Fields for new User record
            String studentId, userId, name, password, email;
            int roleId;
            studentId = student.getUserId();
            name = student.getName();
            userId = studentId;
            password = student.getPassword();
            email = student.getEmail();
            roleId = RoleId.STUDENT;

            stmt = conn.prepareStatement(SQLConstants.USER_ENTRY_QUERY);
            stmt.setString(1, userId);  // This would set age
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setInt(5, roleId);

            if(stmt.executeUpdate()!=1){
                System.out.println("Insertion into User failed !");
                return;
            }
            stmt.close();

            // Fields for Student record
            String address, mobileNumber, departmentID;
            Date dob;
            boolean feeDone, statusApproval, gradeCardApproved;

            address = student.getAddress();
            mobileNumber = student.getMobileNumber();
            departmentID = student.getDepartmentID();
            dob = student.getDob();
            feeDone = student.isFeeDone();
            statusApproval = student.isStatusApproval();
            gradeCardApproved = student.isGradeCardApproved();

            String studentEntryQuery = "INSERT into Student values (?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(studentEntryQuery);
            stmt.setString(1, studentId);  // This would set age
            stmt.setDate(2, dob);
            stmt.setString(3, address);
            stmt.setString(4, mobileNumber);
            stmt.setString(5, departmentID);
            stmt.setBoolean(6, feeDone);
            stmt.setBoolean(7, statusApproval);
            stmt.setBoolean(8, gradeCardApproved);
            stmt.setInt(9, -1);

            if(stmt.executeUpdate()==1){
                System.out.println("You have been added successfully as Student. Please contact admin for approval");
            } else {
                System.out.println("Something went wrong. Please try again");
                return;
            }
            stmt.close();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectPrimaryCourse(ArrayList<Course> primaryCourses) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Fields for updating SemRegistration record
            String userEntryQuery =
                    "insert into SemRegistration values(?,?,?,?,?,?,?,?,?) on duplicate key update pc1=?, pc2=?, pc3=?, pc4=?";

            String studentId = this.student.getUserId();
            System.out.println("Student: " + this.student);
            System.out.println("ID " + this.student.getUserId());
            String pc1 = "", pc2 = "", pc3 = "", pc4 = "";
            try {
                pc1 = primaryCourses.get(0).getCourseID();
                pc2 = primaryCourses.get(1).getCourseID();
                pc3 = primaryCourses.get(2).getCourseID();
                pc4 = primaryCourses.get(3).getCourseID();
            } catch (Exception ignored) {}

            stmt = conn.prepareStatement(userEntryQuery);
            stmt.setString(1, studentId);
            stmt.setBoolean(2, false);
            stmt.setString(3, pc1);
            stmt.setString(4, pc2);
            stmt.setString(5, pc3);
            stmt.setString(6, pc4);
            stmt.setString(7, "");
            stmt.setString(8, "");
            stmt.setString(9, "1");

            stmt.setString(10, pc1);
            stmt.setString(11, pc2);
            stmt.setString(12, pc3);
            stmt.setString(13, pc4);

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<String> viewPrimaryCourses() {
        ArrayList<String> primaryCourses = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String viewPrimaryCoursesQuery = "SELECT pc1, pc2, pc3, pc4 from SemRegistration WHERE studentId='?'";

            stmt = conn.prepareStatement(viewPrimaryCoursesQuery);
            stmt.setString(1, student.getUserId());
            ResultSet rs = stmt.executeQuery(viewPrimaryCoursesQuery);
            while (rs.next()) {
                String pc1 = "", pc2 = "", pc3 = "", pc4 = "";
                try {
                    pc1 = rs.getString("pc1");
                    pc2 = rs.getString("pc2");
                    pc3 = rs.getString("pc3");
                    pc4 = rs.getString("pc4");
                } catch (Exception ignored) {}
                primaryCourses.add(pc1);
                primaryCourses.add(pc2);
                primaryCourses.add(pc3);
                primaryCourses.add(pc4);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong on DB side!");
        }
        return primaryCourses;
    }

    @Override
    public void selectSecondaryCourse(ArrayList<Course> secondaryCourses) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Fields for new SemRegistration record
            String userEntryQuery =
                    "insert into SemRegistration values(?,?,?,?,?,?,?,?,?) " +
                            "on duplicate key update sc1=?, sc2=?";

            String studentId = this.student.getUserId();
            System.out.println("Student: " + this.student);
            System.out.println("ID " + this.student.getUserId());
            String sc1 = "", sc2 = "";
            try {
                sc1 = secondaryCourses.get(0).getCourseID();
                sc2 = secondaryCourses.get(1).getCourseID();
            } catch (Exception ignored) {}

            stmt = conn.prepareStatement(userEntryQuery);
            stmt.setString(1, studentId);
            stmt.setBoolean(2, false);
            stmt.setString(3, "");
            stmt.setString(4, "");
            stmt.setString(5, "");
            stmt.setString(6, "");
            stmt.setString(7, sc1);
            stmt.setString(8, sc2);
            stmt.setString(9, "1");
            stmt.setString(10, sc1);
            stmt.setString(11, sc2);

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<String> viewSecondaryCourses() {
        ArrayList<String> secondaryCourses = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String viewSecondaryCoursesQuery = "SELECT sc1, sc2 from SemRegistration " +
                    "WHERE studentId='" + student.getUserId() + "'";

            stmt = conn.prepareStatement(viewSecondaryCoursesQuery);
            ResultSet rs = stmt.executeQuery(viewSecondaryCoursesQuery);
            while (rs.next()) {
                String sc1 = "", sc2 = "";
                try {
                    sc1 = rs.getString("sc1");
                    sc2 = rs.getString("sc2");
                } catch (Exception ignored) {}
                secondaryCourses.add(sc1);
                secondaryCourses.add(sc2);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong on DB side!");
        }
        return secondaryCourses;
    }

    /*
        Just sets regDone to true in the Student's semRegistration
     */
    @Override
    public void confirmRegistration() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Fields for new SemRegistration record
            String userEntryQuery =
                    "UPDATE SemRegistration SET regDone=1 WHERE studentId=? ";

            String studentId = this.student.getUserId();
            stmt = conn.prepareStatement(userEntryQuery);
            stmt.setString(1, studentId);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCourse(String courseId) {
        try
        {
            stmt = conn.prepareStatement("insert into RegisteredCourse values ( ? , ? )");
            stmt.setString(1, courseId);
            stmt.setString(2, this.student.getUserId());
            stmt.setString(3,"N/A");
            stmt.setString(4,"1");

            stmt.executeUpdate();

            stmt = conn.prepareStatement("update Catalogue set seats = seats-1 where courseId = ?");
            stmt.setString(1, courseId);
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {   try {
            stmt.close();
            conn.close();
            } catch(Exception e){
            e.printStackTrace();
           }
        }
        return false;

    }

    @Override
    public boolean dropCourse(String courseId) {
        try
        {
            stmt = conn.prepareStatement("delete from RegisteredCourse where courseId = ? AND studentId = ?");
            stmt.setString(1, courseId);
            stmt.setString(2, this.student.getUserId());
            stmt.execute();

            stmt = conn.prepareStatement("update Catalogue set availableSeats = availableSeats + 1 where  courseId = ?");
            stmt.setString(1, courseId);
            stmt.execute();

            stmt.close();

            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {   try {
            stmt.close();
            conn.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        }


        return false;
    }

    @Override
    public ArrayList<String> displayRegisteredCourses() {
        ArrayList<String> registeredCourses = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String displayRegisteredCoursesQuery = "SELECT * from RegisteredCourse " +
                    "WHERE studentId='" + student.getUserId() +
                    "' AND semesterId ='1' ";

            stmt = conn.prepareStatement(displayRegisteredCoursesQuery);
            ResultSet rs = stmt.executeQuery(displayRegisteredCoursesQuery);
            while (rs.next()) {
                registeredCourses.add(rs.getString("courseId"));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong on DB side!");
        }
        return registeredCourses;
    }

    public static boolean registrationIsDone(Student student) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String checkReg = "SELECT regDone from SemRegistration where studentId='" + student.getUserId() + "'";
            PreparedStatement stmt = conn.prepareStatement(checkReg);
            ResultSet checkRegRs = stmt.executeQuery(checkReg);
            if (checkRegRs.next()) {
                boolean regDone = checkRegRs.getBoolean("regDone");
                if (regDone) {
                    System.out.println("Your registration is already completed. You are not allowed to perform this operation!");
                }
                return regDone;
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong on DB side");
        }
        return false;
    }

    @Override
    public void displayGradeCard() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String studentId = this.student.getUserId();
            String getGrades =
                        "SELECT courseId, grade FROM RegisteredCourse WHERE studentId=" + "'" + studentId + "'";

            stmt = conn.prepareStatement(getGrades);
            ResultSet rs = stmt.executeQuery(getGrades);
            while (rs.next()) {
                String courseId = rs.getString("courseId");
                String grade = rs.getString("grade");
                System.out.println("Your grade in course #" + courseId + " is " + grade);
            }
            stmt.close();

            String getSGPA = "SELECT SGPA from GradeCard WHERE studentId=" + "'" + studentId + "'";
            PreparedStatement stmt2 = conn.prepareStatement(getSGPA);

            ResultSet rs2 = stmt2.executeQuery(getSGPA);
            if (rs2.next()) {
                float sgpa = rs2.getFloat("SGPA");
                System.out.println("Your SGPA is: " + sgpa);
            }

            stmt2.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            for (Course c: registeredCourses) {
                String userEntryQuery =
                        "INSERT into RegisteredCourse values (?,?,?,?)";

                String courseId = c.getCourseID();
                String studentId = this.student.getUserId();

                stmt = conn.prepareStatement(userEntryQuery);
                stmt.setString(1, courseId);
                stmt.setString(2, studentId);
                stmt.setString(3, "N/A");
                stmt.setString(4, "1");

                stmt.executeUpdate();
                stmt.close();
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

