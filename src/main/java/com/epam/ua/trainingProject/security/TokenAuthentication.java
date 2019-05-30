package com.epam.ua.trainingProject.security;

import com.epam.ua.trainingProject.models.User;
import io.jsonwebtoken.Claims;

public interface TokenAuthentication {
    String createAccessToken(User userAccount);
    Claims getJWTAccessTokenClaims(String token);
}
