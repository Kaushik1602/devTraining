package com.example.phonems.service;

import com.example.phonems.entity.Contact;

import java.util.List;

public interface IService {

    public Contact addContact(Contact contact);

    public List<Contact> displayAll();

    public Contact searchContactByGivenPhoneNo(Long number);

    public List<Contact> searchContactByFirstName(String firstName);

    public Contact removeContact(Long number);

}
