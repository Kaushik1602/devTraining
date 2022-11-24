package com.example.phonems.service;

import com.example.phonems.entity.Contact;

import java.util.List;

public interface IService {

    String addContact(Contact contact);

    List<Contact> displayAll();

    Contact searchContactByGivenPhoneNo(long number);

    List<Contact> searchContactByFirstName(String firstName);

    Contact removeContact(long number);

    Contact updateEmail(long number, String email);

}
