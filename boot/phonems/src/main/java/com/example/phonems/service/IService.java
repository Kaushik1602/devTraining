package com.example.phonems.service;

import com.example.phonems.entity.Contact;

import java.util.List;

public interface IService {

     String addContact(Contact contact);

     List<Contact> displayAll();

     Contact searchContactByGivenPhoneNo(long number);

     List<Contact> searchContactByFirstName(String firstName);

   String removeContact(long phoneNumber);

     String updateEmail(long number, String email);

}
