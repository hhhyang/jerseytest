package com.javaexam.jerseytest.config;

import com.javaexam.jerseytest.resource.JerseyAppResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class JerseyApplication extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(JerseyApplication.class);

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();
        classes.add(JerseyAppResource.class);

        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return super.getSingletons();
    }

    @Override
    public Map<String, Object> getProperties() {
        return super.getProperties();
    }
}
