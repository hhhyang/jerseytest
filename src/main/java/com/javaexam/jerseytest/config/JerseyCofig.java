package com.javaexam.jerseytest.config;

import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

// @Configuration
public class JerseyCofig {

    @Bean
    public ServletRegistrationBean jerseyServlet() {
        //手动注册servlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean<>(new ServletContainer(), "/good/*");
        registrationBean.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyApplication.class.getName());
        registrationBean.setLoadOnStartup(1);

        return registrationBean;
    }


    @Bean
    public ServletListenerRegistrationBean<EnvironmentLoaderListener> shiroListener() {

        ServletListenerRegistrationBean<EnvironmentLoaderListener> listener = new ServletListenerRegistrationBean<>();
        listener.setListener(new EnvironmentLoaderListener());
        return listener;
    }

    @Bean
    public FilterRegistrationBean<ShiroFilter> shiroFilter() {

        FilterRegistrationBean<ShiroFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new ShiroFilter());
        List<String> a = new ArrayList<>();
        a.add("/*");
        filter.setUrlPatterns(a);

        return filter;
    }



}
