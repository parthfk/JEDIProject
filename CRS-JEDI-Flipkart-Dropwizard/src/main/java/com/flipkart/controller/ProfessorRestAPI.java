package com.flipkart.controller;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API endpoint for professor
 */
@Path("/professor")
public class ProfessorRestAPI {

    @GET
    @Path("/viewSelectedCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewSelectedCourses(@QueryParam("professorId") String professorId) {
        ProfessorService profService = new ProfessorServiceOperation(professorId);
        List<String> res = new ArrayList<>();
        List<Course> courses = profService.viewCourseList();
        if (courses.isEmpty())
            return Response.status(200).entity("Not yet registered for any course").build();

        courses.forEach(course -> {
            res.add("CourseId: " + course.getCourseID() +
                    ", CourseName: " + course.getName() +
                    ", ProfessorId: " + course.getProfessorID() +
                    ", Available Seats: " + course.getAvailableSeats());
        });

        return Response.status(200).entity(res).build();
    }

    @GET
    @Path("/viewEnrolledStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewEnrolledStudents(@QueryParam("professorId") String professorId,@QueryParam("courseId") String courseId,@QueryParam("semesterId")String semId){
        ProfessorService profService = new ProfessorServiceOperation(professorId);
        List<String> res = new ArrayList<>();
        List<Student> students = profService.viewEnrolledStudentList(courseId,semId);
        if (students.isEmpty())
            return Response.status(200).entity("No students have registered for this course yet").build();

        students.forEach(student -> {
            res.add("Student ID: " + student.getUserId() +
                    ", Student Name: " + student.getName());
        });

        return Response.status(200).entity(res).build();
    }
}
