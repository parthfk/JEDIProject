package com.flipkart;

import com.flipkart.bean.Admin;
import com.flipkart.controller.AdminRestAPI;
import com.flipkart.controller.HelloRestApi;
import com.flipkart.controller.StudentRestAPI;
import com.flipkart.controller.TestClass;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");

        //registering all the RESTful service classes.
        e.jersey().register(TestClass.class);
        e.jersey().register(new HelloRestApi());
        e.jersey().register(new StudentRestAPI());
        e.jersey().register(new AdminRestAPI());
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}