package com.epam.ua.trainingProject.utils.token;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenConstants {
    public static final String TOKEN_TYPE_KEY = "TOKEN_TYPE";
    public static final String JWT_TOKEN_HEADER = "x-auth-token";
    public static final String JWT_TOKEN_CACHE = "JWTTokenCache";
    public static final String BLACKLISTED_JWT_TOKEN_CACHE = "BlacklistedJWTTokenCache";
}