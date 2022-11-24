package com.example.phonems.service;

import com.example.phonems.entity.Contact;
import com.example.phonems.validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl implements IService{

    private List<Contact> contactList = new ArrayList<>();

    @Autowired
    Validation validation;

    @Override
    public String addContact(Contact contact) {
        Contact contactDetails = new Contact();

        if (!validation.validateFirstName(contact.getFirstName()))
            return "Enter valid Firstname";
        contactDetails.setFirstName(contact.getFirstName());

        if (!validation.validateLastName(contact.getLastName()))
            return "Enter valid Lastname";
        contactDetails.setLastName(contact.getLastName());

        if (!validation.validateEmail(contact.getEmailId()))
            return "Enter valid Email";
        contactDetails.setEmailId(contact.getEmailId());

        if (!validation.validateNumber(contact.getPhoneNumber()))
            return "Enter valid Phone Number";
        contactDetails.setPhoneNumber(contact.getPhoneNumber());

        contactList.add(contactDetails);
        return "Contact Added";
    }

    @Override
    public List<Contact> displayAll() {
        return contactList;
    }

    @Override
    public Contact searchContactByGivenPhoneNo(long number) {
        Contact result = new Contact();
        for (Contact contact:contactList){
            if (contact.getPhoneNumber()==number){
                result = contact;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Contact> searchContactByFirstName(String firstName) {
        List<Contact> result=new ArrayList<>();
        for (Contact contact:contactList){
            if (contact.getFirstName().equals(firstName)){
                result.add(contact);
            }
        }
        return result;
    }

    @Override
    public Contact removeContact(long number) {
        Contact removeContact = searchContactByGivenPhoneNo(number);
        contactList.remove(removeContact);
        return removeContact;
    }

    @Override
    public Contact updateEmail(long number, String email) {
        for (Contact contact:contactList){
            if (contact.getPhoneNumber()==number){
                contact.setEmailId(email);
                return contact;
            }
        }
        return null;
    }
}
