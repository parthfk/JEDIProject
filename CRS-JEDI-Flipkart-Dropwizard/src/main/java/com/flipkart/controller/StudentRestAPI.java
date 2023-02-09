package com.flipkart.controller;


import com.flipkart.bean.Student;
import com.flipkart.service.StudentServiceOperation;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
public class StudentRestAPI {
    //return Response.status(201).entity("Admin added successfully !").build();

    private StudentServiceOperation studentServiceOperation = StudentServiceOperation.getInstance();

    private static boolean isRegistered = false;
    private Student s;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/signup/")
    public Response signup(@QueryParam("departmentId") String departmentId,
                                   @QueryParam("address") String address,
                                   @QueryParam("name") String name,
                                   @QueryParam("password") String password,
                                   @QueryParam("email") String email,
                                   @QueryParam("mobileNumber") String mobileNumber,
                                   @QueryParam("dob") String dob
                                   ) throws ValidationException {
        Date dob1 = Date.valueOf(dob);
        s = new Student(name, email, password, departmentId, address, mobileNumber, dob1);
        studentServiceOperation.signup(s);
        return Response.status(202).entity(s).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register/addCourses")
    public Response addCourses(@QueryParam("pc1") String pc1,
                                   @QueryParam("pc2") String pc2,
                                   @QueryParam("pc3") String pc3,
                                   @QueryParam("pc4") String pc4,
                               @QueryParam("sc1") String sc1,
                               @QueryParam("sc2") String sc2,
                               @QueryParam("studentId") String studentId

    ) throws ValidationException {
        studentServiceOperation.selectPrimaryCourse(studentId, pc1, pc2, pc3, pc4);
        studentServiceOperation.selectSecondaryCourse(studentId, sc1, sc2);
        return Response.status(202).entity("Done").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register/confirm")
    public Response confirm(@QueryParam("studentId") String studentId) throws ValidationException {
        studentServiceOperation.confirmRegistration(studentId);
        return Response.status(202).entity("Done").build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register/removeCourse")
    public Response removeCourse(@QueryParam("studentId") String studentId,
                                  @QueryParam("type") String type,
                                  @QueryParam("courseId") String courseId) throws ValidationException {
        studentServiceOperation.removeCourseFromCart(studentId, type, courseId);
        return Response.status(202).entity("Done").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register/payFee")
    public Response payFee(@QueryParam("studentId") String studentId,
                                 @QueryParam("modeOfPayment") String modeOfPayment,
                           @QueryParam("upiId") String upiId,
                           @QueryParam("cardNumber") String cardNumber,
                           @QueryParam("expiry") String expiryDate,
                           @QueryParam("cvv") String cvv,
                           @QueryParam("bankName") String bankName,
                           @QueryParam("accId") String accId,
                           @QueryParam("password") String password,
                           @QueryParam("checkNo") String checkNo,
                           @QueryParam("receiptNo") String receiptNo
                           ) throws ValidationException {


        studentServiceOperation.payFee(studentId, modeOfPayment, upiId, cardNumber, expiryDate, cvv, bankName, accId, password, checkNo, receiptNo);


        return Response.status(202).entity("Done").build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeCard")
    public Response getGradeCard(@QueryParam("studentId") String studentId) throws ValidationException {
        return Response.status(202).entity(studentServiceOperation.displayGradeCard(studentId)).build();
    }
}
