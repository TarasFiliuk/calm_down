package com.epam.ua.trainingProject.security;

import com.epam.ua.trainingProject.models.User;
import com.epam.ua.trainingProject.properties.ApplicationProperties;
import com.epam.ua.trainingProject.properties.JWTProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

import static com.epam.ua.trainingProject.utils.token.TokenConstants.TOKEN_TYPE_KEY;
import static com.epam.ua.trainingProject.utils.token.TokenType.ACCESS;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.lang.String.valueOf;
import static java.util.Objects.isNull;
import static sun.security.krb5.internal.KerberosTime.now;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenAuthenticationImpl implements TokenAuthentication {
    private final ApplicationProperties applicationProperties;

    @Override
    public String createAccessToken(User userAccount) {
        Date currentDate = now().toDate();
        JWTProperties jwtProperties = applicationProperties.getJwt();
        return Jwts.builder()
                .claim(TOKEN_TYPE_KEY, ACCESS)
                .claim("timestamp", Timestamp.from(currentDate.toInstant()))
                .setSubject(valueOf(userAccount.getId()))
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + jwtProperties.getAccessTokenDuration()))
                .setIssuer(jwtProperties.getTokenIssuer())
                .signWith(HS512, jwtProperties.getTokenSecret())
                .compact();
    }

    @Cacheable(value = "JWTTokenCache", key = "#token")
    public Claims getJWTAccessTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(applicationProperties.getJwt().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}

