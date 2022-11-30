package com.example.phonems.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class EnterValidDataException extends  RuntimeException{
    public EnterValidDataException(String exception){
        super(exception);
    }
}
