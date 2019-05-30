package com.epam.ua.trainingProject.facade;

import com.epam.ua.trainingProject.converter.UserConverter;
import com.epam.ua.trainingProject.dto.UserDto;
import com.epam.ua.trainingProject.models.User;
import com.epam.ua.trainingProject.request.UserRequest;
import com.epam.ua.trainingProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserFacade {
    private final UserConverter userConverter;
    private final UserService userService;

    public UserDto saveNewUser(UserRequest user) {
        return userConverter.convertUser(userService.save(user));
    }

    public UserDto updateUser(UserRequest userRequest) {
        return userConverter.convertUser(userService.updateUser(userRequest));
    }

    public UserDto getUser(int id) {
        return userConverter.convertUser(userService.getUser(id));
    }

    public List<UserDto> findAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers.stream().map(userConverter::convertUser).collect(Collectors.toList());
    }

    public void deleteUser(int id) {
        userService.deleteUser(id);
    }

    public UserDto registerNewUserAccount(User user) {
        return userConverter.convertUser(userService.registerNewUserAccount(user));
    }

    public boolean emailExist(String email) {
        return userService.emailExist(email);
    }

    public boolean activate(String code) {
        return userService.activateUser(code);
    }
}
