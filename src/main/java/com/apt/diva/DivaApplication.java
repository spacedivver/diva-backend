package com.apt.diva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DivaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DivaApplication.class, args);
    }

}
