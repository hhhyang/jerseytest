package com.javaexam.jerseytest.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/jersey")
public class JerseyAppResource {

    @GET
    @Path("/hehe")
    @Produces(MediaType.APPLICATION_JSON)
    public String hehe() {
        return "hehe";
    }

}
