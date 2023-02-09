package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.RoleId;
import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.StudentAlreadyExistException;
import com.flipkart.utils.DbConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.Formatter;

public class StudentDAOImpl implements StudentDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private Student student;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private StudentDAOImpl(Student student) {
        this.student = student;
        this.conn = DbConnection.getInstance().getConnection();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent(){return this.student;}

    // Singleton Pattern
    private static StudentDAOImpl dao = null;

    public static StudentDAOImpl getInstance(Student student) {
        if (dao != null) return dao;
        return dao = new StudentDAOImpl(student);
    }

    @Override
    public void signup(Student student) {

        try {
            // Fields for new User record
            String studentId, userId, name, password, email;
            int roleId;
            studentId = student.getUserId();
            name = student.getName();
            userId = studentId;
            password = student.getPassword();
            email = student.getEmail();
            roleId = RoleId.STUDENT;

            String ss1="SELECT * FROM User where email='"+email +"'";
            stmt=conn.prepareStatement(ss1);

            ResultSet rs1 = stmt.executeQuery(ss1);

            if (rs1.next()) {
                try{
                    throw new StudentAlreadyExistException(email);
                }
                catch (StudentAlreadyExistException e) {
                    System.out.println(e.getMessage());
                }
                finally {
                    return ;
                }
            }

            stmt = conn.prepareStatement(SQLConstants.USER_ENTRY_QUERY);
            stmt.setString(1, userId);  // This would set age
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setInt(5, roleId);

            if (stmt.executeUpdate() != 1) {
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


            stmt = conn.prepareStatement(SQLConstants.STUDENT_ENTRY_QUERY);
            stmt.setString(1, studentId);  // This would set age
            stmt.setDate(2, dob);
            stmt.setString(3, address);
            stmt.setString(4, mobileNumber);
            stmt.setString(5, departmentID);
            stmt.setBoolean(6, feeDone);
            stmt.setBoolean(7, statusApproval);
            stmt.setBoolean(8, gradeCardApproved);
            stmt.setInt(9, -1);

            if (stmt.executeUpdate() == 1) {
                System.out.println("You have been added successfully as Student. Please contact admin for approval");
            } else {
                System.out.println("Something went wrong. Please try again");
                return;
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectPrimaryCourse(String studentId, ArrayList<String> primaryCourses) {
        try {
            // Fields for updating SemRegistration record
            String pc1 = "", pc2 = "", pc3 = "", pc4 = "";
            try {
                pc1 = primaryCourses.get(0);
                pc2 = primaryCourses.get(1);
                pc3 = primaryCourses.get(2);
                pc4 = primaryCourses.get(3);
            } catch (Exception ignored) {}

            stmt = conn.prepareStatement(SQLConstants.NEW_USER_ENTRY_QUERY);
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<String> viewPrimaryCourses(String studentId) {
        ArrayList<String> primaryCourses = new ArrayList<>();
        try {
            String viewPrimaryCoursesQuery = "SELECT pc1, pc2, pc3, pc4 from SemRegistration WHERE studentId='" + studentId + "'";

            stmt = conn.prepareStatement(viewPrimaryCoursesQuery);
            ResultSet rs = stmt.executeQuery(viewPrimaryCoursesQuery);
            while (rs.next()) {
                String pc1 = "", pc2 = "", pc3 = "", pc4 = "";
                try {
                    pc1 = rs.getString("pc1");
                    pc2 = rs.getString("pc2");
                    pc3 = rs.getString("pc3");
                    pc4 = rs.getString("pc4");
                } catch (Exception ignored) {
                }
                primaryCourses.add(pc1);
                primaryCourses.add(pc2);
                primaryCourses.add(pc3);
                primaryCourses.add(pc4);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong on DB side!");
        }
        return primaryCourses;
    }

    @Override
    public void selectSecondaryCourse(String studentId, ArrayList<String> secondaryCourses) {
        try {
            // Fields for new SemRegistration record
            String sc1 = "", sc2 = "";
            try {
                sc1 = secondaryCourses.get(0);
                sc2 = secondaryCourses.get(1);
            } catch (Exception ignored) {
            }

            stmt = conn.prepareStatement(SQLConstants.NEW_USER_ENTRY_QUERY_2);
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<String> viewSecondaryCourses(String studentId) {
        ArrayList<String> secondaryCourses = new ArrayList<>();
        try {
            String viewSecondaryCoursesQuery = "SELECT sc1, sc2 from SemRegistration " +
                    "WHERE studentId='" + studentId + "'";

            stmt = conn.prepareStatement(viewSecondaryCoursesQuery);
            ResultSet rs = stmt.executeQuery(viewSecondaryCoursesQuery);
            while (rs.next()) {
                String sc1 = "", sc2 = "";
                try {
                    sc1 = rs.getString("sc1");
                    sc2 = rs.getString("sc2");
                } catch (Exception ignored) {
                }
                secondaryCourses.add(sc1);
                secondaryCourses.add(sc2);
            }
            stmt.close();
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
    public void confirmRegistration(String studentId) {
        try {
            // Fields for new SemRegistration record
            stmt = conn.prepareStatement(SQLConstants.UPDATE_USER_ENTRY_QUERY);
            stmt.setString(1, studentId);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCourse(String courseId) {
        try {
            stmt = conn.prepareStatement(SQLConstants.INSERT_REGISTERED_COURSE_QUERY);
            stmt.setString(1, courseId);
            stmt.setString(2, this.student.getUserId());
            stmt.setString(3, "N/A");
            stmt.setString(4, "1");

            stmt.executeUpdate();

            stmt = conn.prepareStatement(SQLConstants.UPDATE_CATALOGUE_QUERY);
            stmt.setString(1, courseId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean dropCourse(String courseId) {
        try {
            stmt = conn.prepareStatement(SQLConstants.DELETE_REGISTERED_COURSE_QUERY);
            stmt.setString(1, courseId);
            stmt.setString(2, this.student.getUserId());
            stmt.execute();

            stmt = conn.prepareStatement(SQLConstants.UPDATE_CATALOGUE_QUERY_2);
            stmt.setString(1, courseId);
            stmt.execute();

            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<String> displayRegisteredCourses(String studentId) {
        ArrayList<String> registeredCourses = new ArrayList<>();
        try {
            String displayRegisteredCoursesQuery = "SELECT * from RegisteredCourse " +
                    "WHERE studentId='" + studentId +
                    "' AND semesterId ='1' ";

            stmt = conn.prepareStatement(displayRegisteredCoursesQuery);
            ResultSet rs = stmt.executeQuery(displayRegisteredCoursesQuery);
            while (rs.next()) {
                registeredCourses.add(rs.getString("courseId"));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong on DB side!");
        }
        return registeredCourses;
    }

    public boolean registrationIsDone(Student student) {
        try {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong on DB side");
        }
        return false;
    }

    @Override
    public String displayGradeCard(String studentId) {
        try {
            String output ="";
            //Check if gradecard is approved

            stmt = conn.prepareStatement(SQLConstants.GRADECARD_GENERATED);
            stmt.setString(1,studentId);

            boolean hasGradeCardBeenGenerated = false;

            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                hasGradeCardBeenGenerated = rs.getBoolean(1);

            if(!hasGradeCardBeenGenerated){
                return "GradeCard not generated yet ! Please contact the admin.";
            }

            String getGrades =
                    "SELECT courseId, grade FROM RegisteredCourse WHERE studentId=" + "'" + studentId + "'";

            stmt = conn.prepareStatement(getGrades);

            ResultSet rs0 = stmt.executeQuery(getGrades);
            while (rs0.next()) {
                String grade = rs0.getString("grade");
                if(grade=="N/A")
                {
                    throw new GradeNotAddedException(studentId);
                }

            }

             rs = stmt.executeQuery(getGrades);

            StringBuffer buffer = new StringBuffer();
            Formatter fmt = new Formatter();

            fmt.format("\n%14s %14s\n", "Course ID", "Grade");

            while (rs.next()) {
                String courseId = rs.getString("courseId");
                String grade = rs.getString("grade");
                fmt.format("%14s %14s\n", courseId,grade);
            }
            System.out.println(fmt);
            output += fmt;
            buffer.setLength(0);
            stmt.close();

            String getSGPA = "SELECT SGPA from GradeCard WHERE studentId=" + "'" + studentId + "'";

            PreparedStatement stmt2 = conn.prepareStatement(getSGPA);
            ResultSet rs2 = stmt2.executeQuery(getSGPA);
            if (rs2.next()) {
                float sgpa = rs2.getFloat("SGPA");
                System.out.println("Your SGPA is: " + sgpa);
                output += "Your SGPA is: " + sgpa;
            }

            stmt2.close();
            return output;
        } 
        catch (GradeNotAddedException e) {
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return "Error";
    }

    @Override
    public void setRegisteredCourses(String studentId, ArrayList<Course> registeredCourses) {
        try {
            for (Course c : registeredCourses) {
                String courseId = c.getCourseID();

                stmt = conn.prepareStatement(SQLConstants.INSERT_IN_REGISTERED_COURSE);
                stmt.setString(1, courseId);
                stmt.setString(2, studentId);
                stmt.setString(3, "N/A");
                stmt.setString(4, "1");

                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

