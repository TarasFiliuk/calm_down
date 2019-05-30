package com.epam.ua.trainingProject.converter;

import com.epam.ua.trainingProject.dto.UserDto;
import com.epam.ua.trainingProject.models.Role;
import com.epam.ua.trainingProject.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDto convertUser(User user){
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setFirstName(user.getUsername());
        userDTO.setLastName(user.getSurname());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
