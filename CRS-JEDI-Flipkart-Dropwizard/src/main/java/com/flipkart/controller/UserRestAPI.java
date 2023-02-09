package com.flipkart.controller;
import com.flipkart.service.UserServiceOperation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestAPI {
    private final Validator validator;

    public UserRestAPI(Validator validator){
        this.validator=validator;
    }

//    public Response login(){
//        UserServiceOperation obj=new UserServiceOperation();
//        if(obj.logIn(email,password,role)==null){
//            //return "failure";
//        }
//        else {
//          //  return "success";
//        }
//    }
//
//    public Response logout(){
//        UserServiceOperation obj=new UserServiceOperation();
//        if(obj.logOut()) {
//            // return "logout successfull";
//        }
//    }
}
