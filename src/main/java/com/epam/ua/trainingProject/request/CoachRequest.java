package com.epam.ua.trainingProject.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoachRequest {
    private int id;
    private String name;
    private String surName;
    private String email;
    private String experience;
}
