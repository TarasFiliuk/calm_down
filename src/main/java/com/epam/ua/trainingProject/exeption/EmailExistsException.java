package com.epam.ua.trainingProject.exeption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailExistsException extends RuntimeException {
    private String field;

    public EmailExistsException(String field) {
        this.field = field;
    }

    public EmailExistsException(){
        super();
    }
    public EmailExistsException(String field, String massage){
        super(massage);
        this.field=field;
    }
}
