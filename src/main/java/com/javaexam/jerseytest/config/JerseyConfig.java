package com.javaexam.jerseytest.config;

import com.javaexam.jerseytest.resource.JerseyResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


@Component
// 所有资源的公共父路径
@ApplicationPath("hhh")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        // 注册指定的resource
        register(JerseyResource.class);
        // 在指定的package下扫描，添加带有 @Path @Provider 注解的类
        packages("com.javaexam.jerseytest.resource");
    }

}
