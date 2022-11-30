package com.example.phonems.exceptions;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(String execption){
        super(execption);
    }
}
