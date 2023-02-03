package com.flipkart.dao;
import com.flipkart.bean.Course;

import com.flipkart.bean.Professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.flipkart.constant.DBConnection.*;

public class ProfessorDAOImpl implements ProfessorDAO{
    private Professor prof;

    public ProfessorDAOImpl(Professor prof) {
        this.prof = prof;
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (SQLException e){

        }
    }

    @Override
    public void selectCourseDAO(Course course) {
        String profId = prof.getUserId();
        String courseId = course.getCourseID();
        String updateCourseQuery = "UPDATE Catalogue SET professorId="+ profId + " WHERE courseId="+ courseId;
        String updateProfessorQuery = "UPDATE Catalogue SET professorId="+ profId + " WHERE courseId="+ courseId;
    }
}
