package com.epam.ua.trainingProject.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private int id;
    private String name;
    private String email;
    private String surName;
    private String password;
}
