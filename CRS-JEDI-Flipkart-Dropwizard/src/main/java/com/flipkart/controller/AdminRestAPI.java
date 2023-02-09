package com.flipkart.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.flipkart.bean.*;
import com.flipkart.exception.AdminAlreadyExistException;
import com.flipkart.exception.AdminNotAddedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorAlreadyExistException;
import com.flipkart.service.AdminService;
import com.flipkart.service.AdminServiceOperation;
//import com.flipkart.service.UserServiceOperation;
import com.flipkart.service.UserServiceOperation;
import org.jetbrains.annotations.NotNull;

@Path("/admin")
public class AdminRestAPI {

    AdminService adminService = new AdminServiceOperation();


    @POST
    @Path("/addAdmin")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAdmin(@Valid Admin newAdmin) throws ValidationException{

        try {
            adminService.addAdmin(newAdmin);
            return Response.status(201).entity("Admin added successfully !").build();

        } catch (AdminAlreadyExistException | AdminNotAddedException| SQLException e) {

            return Response.status(409).entity(e.getMessage()).build();

        }

    }

    @POST
    @Path("/addProfessor")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(@Valid Professor newProf) throws ValidationException{

        try {
            adminService.addProfessor(newProf);
            return Response.status(201).entity("Professor added successfully !").build();

        } catch (ProfessorAlreadyExistException | SQLException e) {

            return Response.status(409).entity(e.getMessage()).build();

        }

    }

    @PUT
    @Path("/approveStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudent(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException {
        try {
            boolean res = adminService.approveStudent(studentId);
            return Response.status(201).entity("Student with studentId: " + studentId + " approved").build();

        } catch (SQLException e) {
            return Response.status(409).entity(e.getMessage()).build();

        }

    }


    @PUT
    @Path("/deleteCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCourse(
            @NotNull
            @QueryParam("courseId") String courseId) throws ValidationException{
        try {
            adminService.removeCourse(courseId);
            return Response.status(201).entity("Course with courseId: " + courseId + " deleted from catalog").build();

        } catch (CourseNotFoundException | SQLException e) {
            return Response.status(409).entity(e.getMessage()).build();

        }
    }

    /**
     * /admin/addCourse
     * REST-service for adding a new course in catalog
     * @param course
     * @return
     */
    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@Valid Course course) throws ValidationException{
        try {
            //need to take semesterId as input as well
           // if(adminService.addCourse("1",course.getCourseID(),course.getName(),course.getAvailableSeats()))
            adminService.addCourse("1",course.getCourseID(),course.getName(),course.getAvailableSeats());
                return Response.status(201).entity("Course with courseId: " + course.getCourseID() + " added to catalog").build();

        } catch (SQLException e) {

            return Response.status(409).entity(e.getMessage()).build();

        }
    }

        @POST
    @Path("/generateGradeCard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateGradeCard(
            @NotNull
            @QueryParam("studentId") String studentId
            ) throws ValidationException {
        try {
            int response = adminService.generateGradeCard(studentId);
           if(response ==1)
            return Response.status(409).entity("Student hasn't registered courses for the sem yet !").build();
           else if(response ==2) {
               return Response.status(409).entity("Some course(s) are yet to be graded !").build();
           }
            return Response.status(201).entity("GradeCard generated for StudentId: "+ studentId + " successfully.").build();
        } catch (SQLException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/viewCoursesInCatalogue")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCoursesInCatalogue() {
        List<Course> coursesInCatalogue = null;
        try{
            coursesInCatalogue = new UserServiceOperation().viewCourseCatalogue(false);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return coursesInCatalogue;

    }

    @GET
    @Path("/viewCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewAllCourses() {
        List<Course> allCourses = null;
        try{
            allCourses = new UserServiceOperation().viewCourseCatalogue(true);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return allCourses;

    }

    @GET
    @Path("/viewUnApprovedStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<List<String>> viewListOfUnApprovedStudents() {
        List<List<String>> unApprovedStudents = null;
        try{
            return adminService.getUnApprovedStudents();
        }catch(SQLException e){
            unApprovedStudents = new ArrayList<>();
            List<String> msg = new ArrayList<>();
            msg.add(e.getMessage());
            unApprovedStudents.add(msg);
            return unApprovedStudents;
        }
    }

    @GET
    @Path("/viewAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try{
        allUsers = new UserServiceOperation().getAllUsers();
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return allUsers;
    }

//
//    /**
//     *
//     * @return list of professors in the system
//     */
//    @GET
//    @Path("/viewProfessors")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Professor> viewProfessors() {
//
//        return adminOperation.viewProfessors();
//    }
//

}
