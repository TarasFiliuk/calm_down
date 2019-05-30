package com.epam.ua.trainingProject.error;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Errors {

    @ApiModelProperty("Errors")
    private List<Error> errors = new ArrayList<>();

    public void addFieldError(String path, String message) {
        Error error = new Error(path, message);
        this.errors.add(error);
    }
}