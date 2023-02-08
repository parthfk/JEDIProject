package com.flipkart.controller;

import com.flipkart.exception.ProfessorNotFoundException;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * API endpoint for professor
 */
@Path("/professor")
public class ProfessorRestAPI {

    @GET
    @Path("/viewSelectedCourses")
    public Response viewSelectedCourses(@QueryParam("professorId") int professorId) {
        try {

        } catch (ProfessorNotFoundException e){

        }
    }

}
