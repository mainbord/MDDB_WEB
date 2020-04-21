package com.mddb;

import com.mddb.service.DbServicesImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
//        context.getBean(DbServicesImpl.class).saveDevices();
    }
}
