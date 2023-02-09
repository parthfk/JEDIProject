package com.flipkart.controller;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.*;
import com.flipkart.service.UserService;
import com.flipkart.service.UserServiceOperation;
import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @author dilpreetkaur
 *
 */

@Path("/user")
public class UserRestAPI {
    UserService userServiceOperation = new UserServiceOperation();


    /**
     *
     * @param userId: email address of the user
     * @param newPassword: new password to be stored in db.
     * @return @return 201, if password is updated, else 500 in case of error
     */
    @GET
    @Path("/updatePassword")
    public Response updatePassword(
            @NotNull
            @Email(message = "Invalid User ID: Not in email format")
            @QueryParam("userId") String userId,
            @NotNull
            @QueryParam("oldPassword") String oldPassword,
            @NotNull
            @QueryParam("newPassword") String newPassword,
            @NotNull
            @QueryParam("role") String role
    ) throws ValidationException {
        try {
            if(userServiceOperation.updatePassword(userId, oldPassword, newPassword, role))
                return Response.status(201).entity("Password updated successfully! ").build();
            else
                return Response.status(201).entity("Password updated failed! ").build();
        }catch(UserNotFoundException | PasswordMismatchException| SQLException e){
            return Response.status(500).entity(e.getMessage()).build();
        }


    }

    /**
     *
     * @param email
     * @param password
     * @param role
     * @return
     */

    @POST
    @Path("/login")
    public Response verifyCredentials(
            @NotNull
            @Email(message = "Invalid User ID: Not in email format")
            @QueryParam("email") String email,
            @NotNull
            @QueryParam("password") String password,
            @NotNull
            @QueryParam("role")String role
    ) throws ValidationException {

        try{
            User user = userServiceOperation.logIn(email,password,role);
            if(user != null){
                return Response.status(201).entity("User "+ user.getName() + " with role "+user.getUserType()+" logged" +
                        " in successfully.").build();
            }
            else return Response.status(500).entity("Login failed !").build();
        }catch(AdminNotFoundException | StudentNotFoundException| ProfessorNotFoundException |StudentNotApprovedException | RoleMismatchException e){
            return Response.status(500).entity(e.getMessage()).build();
        }

    }

    @POST
    @Path("/logout")
    public Response logOut(){
        return Response.status(200).entity("Logged out successfully !").build();

    }

    @GET
    @Path("/viewAllCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewAllCourses() {
        List<Course> coursesInCatalogue = null;
        coursesInCatalogue = new UserServiceOperation().viewCourseCatalogue(true);
        return coursesInCatalogue;

    }

    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try{
            allUsers = userServiceOperation.getAllUsers();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return allUsers;
    }

}