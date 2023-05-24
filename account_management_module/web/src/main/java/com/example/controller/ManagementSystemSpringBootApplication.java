package com.example.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.example.repository"})
@EntityScan(basePackages = {"com.example.entity"})
@ComponentScan(basePackages = {"com.example.repository", "com.example.entity", "com.example.services","com.example.controller" } )

@SpringBootApplication
public class ManagementSystemSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementSystemSpringBootApplication.class, args);
    }

}
