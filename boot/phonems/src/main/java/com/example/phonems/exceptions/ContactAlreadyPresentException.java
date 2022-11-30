package com.example.phonems.exceptions;

public class ContactAlreadyPresentException extends RuntimeException{

    public ContactAlreadyPresentException(String msg){
        super(msg);
    }
}
