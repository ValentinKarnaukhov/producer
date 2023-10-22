package com.github.valentinkarnaukhov.education.producer.handler;

import com.github.valentinkarnaukhov.education.producer.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse entityNotFoundException(EntityNotFoundException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(exception.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse exception(Exception exception) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(exception.getMessage());
        return response;
    }

}
