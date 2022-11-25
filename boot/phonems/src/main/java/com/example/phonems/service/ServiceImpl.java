package com.example.phonems.service;

import com.example.phonems.entity.Contact;
import com.example.phonems.validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl implements IService {

    private List<Contact> contactList = new ArrayList<>();
    Contact contact = new Contact();
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

        if (!validation.validateAge(contact.getAge()))
            return "Enter valid Age";
        contactDetails.setAge(contact.getAge());

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
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber() == number) {
                result = contact;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Contact> searchContactByFirstName(String firstName) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getFirstName().equals(firstName)) {
                result.add(contact);
            }
        }
        return result;
    }

    @Override
    public String removeContact(long phoneNumber) {
        String string = String.valueOf(phoneNumber);
        if (string.length() != 10) {
            return "enter correct phone number";
        }
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber() == phoneNumber) {
                contactList.remove(contact);
                return "contact removed.";
            }
        }
        return "Phone number not found.";
    }

    @Override
    public String updateEmail(long phoneNumber, String email) {
        String string = String.valueOf(phoneNumber);
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber() == phoneNumber) {
                if ((validation.validateEmail(email) && email.length() != 0 && string.length() == 10)) {
                    contact.setEmailId(email);
                    return "email updated successfully";
                }
            }
        }
        return "enter valid details";
    }
}
