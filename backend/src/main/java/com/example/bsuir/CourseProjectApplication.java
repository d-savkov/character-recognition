package com.example.bsuir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class CourseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseProjectApplication.class, args);
    }

}
