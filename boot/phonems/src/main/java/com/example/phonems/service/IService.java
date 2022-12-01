package com.example.phonems.service;

import com.example.phonems.entity.Contact;

import java.util.List;

public interface IService {

    Contact addContact(Contact contact);

    List<Contact> displayAll();

    Contact searchContactByGivenPhoneNo(long number);

    List<Contact> searchContactByFirstName(String firstName);

    String removeContact(long phoneNumber);

    Contact updateEmail(long number, String email);

}
