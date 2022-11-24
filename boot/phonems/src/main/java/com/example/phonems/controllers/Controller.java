package com.example.phonems.controllers;

import com.example.phonems.entity.Contact;
import com.example.phonems.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    IService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customers/add")
    private String add(@RequestBody Contact contact){
        return service.addContact(contact);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customers/view")
    private List<Contact> viewAll(){
        return service.displayAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/customers/findByNumber/{phoneNumber}")
    private Contact findByNumber(@PathVariable long phoneNumber){
        return service.searchContactByGivenPhoneNo(phoneNumber);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/customers/findByName/{firstName}")
    private List<Contact> findByFirstName(@PathVariable String firstName){
        return service.searchContactByFirstName(firstName);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/customers/delete/{phoneNumber}")
    private Contact deleteByNumber(@PathVariable long phoneNumber){
        return service.removeContact(phoneNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/customers/update/{phoneNumber}")
    private Contact updateEmail(@PathVariable long phoneNumber, @RequestBody String emailId){
        return service.updateEmail(phoneNumber,emailId);
    }



}
