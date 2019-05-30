package com.epam.ua.trainingProject.exeption.exeptionHandler;

import com.epam.ua.trainingProject.error.Error;
import com.epam.ua.trainingProject.error.ErrorUtils;
import com.epam.ua.trainingProject.error.Errors;
import com.epam.ua.trainingProject.exeption.EmailExistsException;
import com.epam.ua.trainingProject.exeption.RequestException;
import com.epam.ua.trainingProject.exeption.NotFoundExeption;
import com.epam.ua.trainingProject.exeption.StorageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ControllerExceptionHandler {
    @ExceptionHandler(NotFoundExeption.class)
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    public Errors notFound(NotFoundExeption e) {
        return ErrorUtils.newErrorsList(new Error(e.getField(), e.getMessage()));
    }

    @ExceptionHandler(RequestException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public Errors requestAlreadyExist(RequestException e) {
        return ErrorUtils.newErrorsList(new Error(e.getField(), e.getMessage()));
    }
    @ExceptionHandler(EmailExistsException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public Errors requestAlreadyExist(EmailExistsException e) {
        return ErrorUtils.newErrorsList(new Error(e.getField(), e.getMessage()));
    }

    @ExceptionHandler(StorageException.class)
    public Errors handleStorageFileNotFound(StorageException e) {
        return ErrorUtils.newErrorsList(new Error(e.getField(), e.getMessage()));
    }
}
