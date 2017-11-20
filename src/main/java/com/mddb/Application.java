package com.mddb;

import com.mddb.service.DbServicesImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created by mainbord on 20.09.17.
 */

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.mddb")
public class Application extends SpringBootServletInitializer {



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.getBean(DbServicesImpl.class).saveDevices();
    }
}
