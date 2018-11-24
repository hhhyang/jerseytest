package com.javaexam.jerseytest.config;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 这个类的值从配置文件中读取
 */
@Component
// 匹配的名字的前缀
@ConfigurationProperties(prefix = "people.config")
// 读取配置的文件
@PropertySource({"classpath:people.yml"})
@Data
@Accessors(chain = true)
public class PeopleConfig {

    private String name;
    private int age;

}
