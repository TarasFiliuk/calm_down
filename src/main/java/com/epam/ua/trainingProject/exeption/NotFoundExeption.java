package com.epam.ua.trainingProject.exeption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundExeption extends RuntimeException {
    private String field;
    public NotFoundExeption(){
        super();
    }
    public NotFoundExeption(String field, String massage){
        super(massage);
        this.field=field;
    }
}
