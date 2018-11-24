
package com.javaexam.jerseytest;

import com.javaexam.jerseytest.config.Company;
import com.javaexam.jerseytest.config.PeopleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@EnableConfigurationProperties({PeopleConfig.class})
@SpringBootApplication
public class JerseytestApplication {

	private static final Logger LOG = LoggerFactory.getLogger(JerseytestApplication.class);


	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JerseytestApplication.class, args);


		try {
			// 获取 class文件的位置
			// LOG.error("{}", SpringboottestApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath());

            PeopleConfig peopleConfig = (PeopleConfig) context.getBean("peopleConfig");
            LOG.info("name: {}, age: {}", peopleConfig.getName(), peopleConfig.getAge());

            Company company = (Company) context.getBean("company");
            LOG.info("companyName: {}, people.name: {}", company.getCompanyName(), company.getPeopleConfig().getName());


		} catch (Exception e) {
			LOG.error(e.toString());
		}




	}
}
