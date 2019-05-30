package com.epam.ua.trainingProject.exeption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageException extends RuntimeException {

    private String field;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}