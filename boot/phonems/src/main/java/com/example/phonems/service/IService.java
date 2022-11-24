package com.example.phonems.service;

import com.example.phonems.entity.Contact;

import java.util.List;

public interface IService {

    public String addContact(Contact contact);

    public List<Contact> displayAll();

    public Contact searchContactByGivenPhoneNo(long number);

    public List<Contact> searchContactByFirstName(String firstName);

    public Contact removeContact(long number);

    public Contact updateEmail(long number, String email);

}
