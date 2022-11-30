package com.example.phonems.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(String execption){
        super(execption);
    }
}
