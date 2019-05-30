package com.epam.ua.trainingProject;

import com.epam.ua.trainingProject.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties(ApplicationProperties.class)
@EnableScheduling
@EnableSpringDataWebSupport
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
