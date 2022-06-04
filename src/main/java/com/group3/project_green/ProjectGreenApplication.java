package com.group3.project_green;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProjectGreenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectGreenApplication.class, args);
    }

}

