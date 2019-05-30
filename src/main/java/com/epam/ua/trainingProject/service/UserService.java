package com.epam.ua.trainingProject.service;

import com.epam.ua.trainingProject.models.User;
import com.epam.ua.trainingProject.request.UserRequest;
import com.epam.ua.trainingProject.utils.token.TokenType;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User save(UserRequest user);

    User updateUser(UserRequest userRequest);

    User getUser(int id);

    List<User> getAllUsers();

    void deleteUser(int id);


    Optional<User> findUserByEmail(String email);

    User registerNewUserAccount(User user);

    User getUserAccount(String refreshToken, TokenType tokenType) throws ExpiredJwtException;

    Authentication getUserAccountAuthentication(HttpServletRequest servletRequest);

    boolean emailExist(String email);

    void sendMailToNewUser(User user);

    boolean activateUser(String code);


    Object singInWithGoogle(Map<String, Object> map);
}