package com.flipkart.controller;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceOperation;

import javax.ws.rs.*;
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
        try{
            ProfessorService profService = new ProfessorServiceOperation(professorId);
            List<String> res = new ArrayList<>();
            List<Student> students = profService.viewEnrolledStudentList(courseId, semId);
            if (students.isEmpty())
                return Response.status(200).entity("No students have registered for this course yet").build();

            students.forEach(student -> {
                res.add("Student ID: " + student.getUserId() +
                        ", Student Name: " + student.getName());
            });

            return Response.status(200).entity(res).build();
        }catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/selectCourse")
    @Produces(MediaType.TEXT_PLAIN)
    public Response selectCourse(@QueryParam("professorId") String professorId,@QueryParam("courseId") String courseId)  {

        try {
            ProfessorService profService = new ProfessorServiceOperation(professorId);
            profService.selectCourse(courseId);

        return Response.status(200).entity("Course selected to teach successfully").build();
        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/addGrade")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addGrade(@QueryParam("professorId") String professorId,@QueryParam("courseId") String courseId,@QueryParam("semesterId")String semId,@QueryParam("studentId") String studentId,@QueryParam("grade") String grade){
        try{
            ProfessorService profService = new ProfessorServiceOperation(professorId);
            if (profService.addGrade(courseId, semId, studentId, grade)) {
                return Response.status(200).entity("Course graded successfully").build();
            } else {
                return Response.status(200).entity("Failed to grade course").build();
            }
        }
        catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }

    }

}
