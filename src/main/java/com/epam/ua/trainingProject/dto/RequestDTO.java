package com.epam.ua.trainingProject.dto;

import com.epam.ua.trainingProject.models.Status;
import com.epam.ua.trainingProject.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
    private int id;
    private Status status;
    private String experience;
    private UserDto user;
}
