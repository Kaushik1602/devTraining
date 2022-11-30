package com.example.phonems.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = EnterValidDataException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody ErrorResponse EnterValidDataException(EnterValidDataException e) {
        ErrorResponse response = new ErrorResponse();
        response.setResponseCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setMsg(e.getMessage());
        return response;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ContactNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse ContactNotFoundException(ContactNotFoundException e) {
        ErrorResponse response = new ErrorResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setMsg(e.getMessage());
        return response;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ContactAlreadyPresentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse ContactAlreadyPresentException(ContactAlreadyPresentException e) {
        ErrorResponse response = new ErrorResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setMsg(e.getMessage());
        return response;
    }

}
