package com.epam.ua.trainingProject.exeption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestException extends RuntimeException {
    private String field;
    public RequestException(){
        super();
    }
    public RequestException(String field, String massage){
        super(massage);
        this.field=field;
    }
}
