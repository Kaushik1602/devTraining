package com.example.phonems.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ContactAlreadyPresentException extends RuntimeException{

    public ContactAlreadyPresentException(String msg){
        super(msg);
    }
}
