package com.epam.ua.trainingProject.service.impl;

import com.epam.ua.trainingProject.exeption.EmailExistsException;
import com.epam.ua.trainingProject.exeption.NotFoundExeption;
import com.epam.ua.trainingProject.models.Role;
import com.epam.ua.trainingProject.models.User;
import com.epam.ua.trainingProject.repository.UserRepository;
import com.epam.ua.trainingProject.request.UserRequest;
import com.epam.ua.trainingProject.security.TokenAuthentication;
import com.epam.ua.trainingProject.service.UserService;
import com.epam.ua.trainingProject.utils.Constants;
import com.epam.ua.trainingProject.utils.token.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

import static com.epam.ua.trainingProject.models.Role.ROLE_USER;
import static com.epam.ua.trainingProject.utils.token.TokenConstants.JWT_TOKEN_HEADER;
import static com.epam.ua.trainingProject.utils.token.TokenConstants.TOKEN_TYPE_KEY;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenAuthentication tokenAuthentication;
    private final MailService mailService;

    @Override
    public User save(UserRequest user) {
        if (this.emailExist(user.getEmail())) {
            throw new EmailExistsException("user with email %s already exist", user.getEmail());
        } else {
            User newUser = new User();
            newUser.setUsername(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setRole(ROLE_USER);
            newUser.setSurname(user.getSurName());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            activateAccount(newUser);
//            this.sendMailToNewUser(newUser);
            return userRepository.save(newUser);
        }
    }

    private void activateAccount(User newUser) {
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setEnabled(true);
        newUser.setCredentialsNonExpired(true);
    }

    @Override
    public User updateUser(UserRequest userRequest) {
        User user = this.getUser(userRequest.getId());
        ofNullable(userRequest.getName()).ifPresent(user::setUsername);
        ofNullable(userRequest.getSurName()).ifPresent(user::setSurname);
        ofNullable(userRequest.getPassword()).ifPresent(user::setPassword);
        return userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("user with id : " + id, Constants.NOT_FOUND));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(this.getUser(id));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public User registerNewUserAccount(User user)
            throws EmailExistsException {

        if (emailExist(user.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            + user.getEmail());
        } else return userRepository.save(user);

    }

    @Override
    public User getUserAccount(String refreshToken, TokenType tokenType) throws ExpiredJwtException {
        Claims claims = tokenAuthentication.getJWTAccessTokenClaims(refreshToken);
        if (!claims.get(TOKEN_TYPE_KEY).equals(tokenType.name())) {
            return null;
        }
        return this.getUser(Integer.valueOf(claims.getSubject()));
    }

    @Override
    public Authentication getUserAccountAuthentication(HttpServletRequest servletRequest) {
        String token = servletRequest.getHeader(JWT_TOKEN_HEADER);
        if (nonNull(token)) {
            User userAccount = this.getUserAccount(token, TokenType.ACCESS);
            return nonNull(userAccount)
                    ? new UsernamePasswordAuthenticationToken(userAccount, null, Collections.emptyList())
                    : null;
        }
        return null;
    }

    @Override
    public boolean emailExist(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && StringUtils.isEmpty(email)) {
            return true;
        }
        return false;
    }

    @Override
    public void sendMailToNewUser(User user) {
        user.setActivationCode(UUID.randomUUID().toString());
        String message = String.format("Hello, %s \n" +
                "Welcome to my site.Please,visit next link : http://localhost:8080/activate/%s", user.getUsername(), user.getActivationCode());
        mailService.send(user.getEmail(), "Activation code", message);
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        activateAccount(user);
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    @Override
    public Object singInWithGoogle(Map<String, Object> map) {
        User user = userRepository.findByEmail((String) map.get("email")).orElseGet(() -> {
            User newUser = new User();
            newUser.setUsername((String) map.get("name"));
            newUser.setEmail((String) map.get("email"));
            newUser.setPicture((String) map.get("picture"));
            newUser.setRole(Role.ROLE_USER);
            activateAccount(newUser);
            return newUser;
        });
        user.setLastVisit(LocalDateTime.now());
        return userRepository.save(user);
    }

}

