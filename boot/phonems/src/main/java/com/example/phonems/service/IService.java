package com.example.phonems.service;

import com.example.phonems.entity.Contact;

import java.util.List;

public interface IService {

     String addContact(Contact contact) throws Exception;

     List<Contact> displayAll();

     Contact searchContactByGivenPhoneNo(long number);

     List<Contact> searchContactByFirstName(String firstName);

   String removeContact(long phoneNumber)throws Exception;

     String updateEmail(long number, String email);

}
