package com.epam.ua.trainingProject.dto;

import com.epam.ua.trainingProject.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoachDTO {
    private int id;
    private String name;
    private String surName;
    private String experience;
    List<User> trainee;
}
