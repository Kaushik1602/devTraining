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
    @PostMapping("/contacts/add")
    private Contact add(@RequestBody Contact contact) throws Exception {
        return service.addContact(contact);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/contacts/view")
    private List<Contact> viewAll() {
        return service.displayAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/contacts/findByNumber/{phoneNumber}")
    private Contact findByNumber(@PathVariable long phoneNumber) {
        return service.searchContactByGivenPhoneNo(phoneNumber);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/contacts/findByName/{firstName}")
    private List<Contact> findByFirstName(@PathVariable String firstName) {
        return service.searchContactByFirstName(firstName);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/contacts/delete/{phoneNumber}")
    private String deleteByNumber(@PathVariable long phoneNumber) throws Exception {
        return service.removeContact(phoneNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/contacts/update/{phoneNumber}")
    private Contact updateEmail(@PathVariable long phoneNumber, @RequestBody String emailId) {
        return service.updateEmail(phoneNumber, emailId);
    }


}
