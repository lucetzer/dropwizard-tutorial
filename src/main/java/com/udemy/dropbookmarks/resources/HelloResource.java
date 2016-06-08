package com.udemy.dropbookmarks.resources;

import com.udemy.dropbookmarks.core.User;
import io.dropwizard.auth.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "Hello World!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/secured")
    public String getSecuredGreeting(@Auth User user) {
        return "Hello secured world!";
    }

}
