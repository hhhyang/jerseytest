package com.javaexam.jerseytest.resource;

import com.javaexam.jerseytest.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/jersey")
// 这个资源是单例的，默认是@RequestScope
@Singleton
public class JerseyResource {

    private static final Logger LOG = LoggerFactory.getLogger(JerseyResource.class);

    private int temp = 0;


    @GET
    @Path("/hello")
    @Produces({MediaType.APPLICATION_JSON})
    public String hello() {
        return "hello, Jersey";
    }


    @GET
    @Path("/user")
    @Produces({MediaType.APPLICATION_JSON})
    public User user() {

        temp += 1;
        LOG.info("temp={}", temp);

        User user = new User("yang", 20);
        return user;
    }


    @GET
    @Path("/userXml")
    @Produces({MediaType.APPLICATION_XML})
    public User userXml() {
        User user = new User("yang", 20);
        return user;
    }

}
