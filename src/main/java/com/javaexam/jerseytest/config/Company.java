package com.javaexam.jerseytest.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:people.yml")
@Data
@Accessors(chain = true)
public class Company {

    @Autowired
    private PeopleConfig peopleConfig;

    // 只能从 application.yml 或 application.properties 中获取？
    @Value("${company.name}")
    private String companyName;

}
