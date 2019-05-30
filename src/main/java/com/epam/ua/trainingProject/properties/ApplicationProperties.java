package com.epam.ua.trainingProject.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Priority;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ConfigurationProperties
@Data
@Priority(HIGHEST_PRECEDENCE)
public class ApplicationProperties {
   private JWTProperties jwt;

}
