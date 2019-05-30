package com.epam.ua.trainingProject.properties;

import lombok.Data;

@Data
public class JWTProperties {
    private Long accessTokenDuration;
    private Long refreshTokenDuration;
    private String tokenIssuer;
    private String tokenSecret;

}
