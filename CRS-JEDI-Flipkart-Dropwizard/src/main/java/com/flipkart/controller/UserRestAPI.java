package com.flipkart.controller;

import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
public class UserRestAPI {


private final Validator validator;



public UserRestAPI(Validator validator){
    this.validator=validator;
}





}
