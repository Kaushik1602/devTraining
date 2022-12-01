package com.example.phonems.service;

import com.example.phonems.entity.Contact;
import com.example.phonems.exceptions.ContactAlreadyPresentException;
import com.example.phonems.exceptions.ContactNotFoundException;
import com.example.phonems.validations.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServiceImpl implements IService {

    private List<Contact> contactList = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

    public List<Contact> getContactList() {
        return contactList;
    }

    Contact contact = new Contact();
    @Autowired
    Validation validation;


    @Override
    public Contact addContact(Contact contact) {
        logger.info("method started.");
        Contact contactDetails = new Contact();
        uniqueCheck(contact.getPhoneNumber());
        contactDetails.setFirstName(validation.validateFirstName(contact.getFirstName()));
        contactDetails.setLastName(validation.validateLastName(contact.getLastName()));
        contactDetails.setEmailId(validation.validateEmail(contact.getEmailId()));
        contactDetails.setPhoneNumber(validation.validateNumber(contact.getPhoneNumber()));
        contactDetails.setAge(validation.validateAge(contact.getAge()));
        contactList.add(contactDetails);
        logger.info("Contact added.");
        return contactDetails;
    }

    @Override
    public List<Contact> displayAll() {
        logger.info("method started.");
        if (contactList.size() == 0) {
            logger.warn("List is Empty");
            throw new ContactNotFoundException("No contacts present in list");
        }
        logger.info("Contact list displayed.");
        return contactList;
    }

    @Override
    public Contact searchContactByGivenPhoneNo(long number) {
        logger.info("method started.");
        validation.validateNumber(number);
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber() == number) {
                logger.info("Found Contact");
                return contact;
            }
        }
        throw new ContactNotFoundException("Contact not present" + number);
    }

    @Override
    public List<Contact> searchContactByFirstName(String firstName) {
        logger.info("method started.");
        List<Contact> result = new ArrayList<>();
        validation.validateFirstName(firstName);
        for (Contact contact : contactList) {
            if (contact.getFirstName().equals(firstName)) {
                result.add(contact);
            }
        }
        if (result.size() == 0) {
            throw new ContactNotFoundException("No contacts found");
        }
        logger.info("Found Contact");
        return result;
    }

    @Override
    public String removeContact(long phoneNumber) {
        logger.info("method started.");
        Contact contact1 = null;
        validation.validateNumber(phoneNumber);
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber() == phoneNumber) {
                contact1 = contact;
            }
        }
        if (contact1 == null) {
            throw new ContactNotFoundException("number not found");
        }

        contactList.remove(contact1);
        logger.info("contact deleted.");
        return contact1 + " :contact deleted.";
    }

    @Override
    public Contact updateEmail(long phoneNumber, Contact contact) {
        logger.info("method started.");
        validation.validateNumber(phoneNumber);
        for (Contact contact1 : contactList) {
            if (contact1.getPhoneNumber() == phoneNumber) {
                contact1.setPhoneNumber(validation.validateNumber(contact.getPhoneNumber()));
                contact1.setFirstName(validation.validateFirstName(contact.getFirstName()));
                contact1.setLastName(validation.validateLastName(contact.getLastName()));
                contact1.setAge(validation.validateAge(contact.getAge()));
                contact1.setEmailId(validation.validateEmail(contact.getEmailId()));
                contactList.add(contact1);
                logger.info("Email updated successfully");
                return contact1;
            }
        }
        throw new ContactNotFoundException("Contact not found.");
    }

    public void uniqueCheck(long number) {
        for (Contact contact : getContactList()) {
            if (contact.getPhoneNumber() == number)
                throw new ContactAlreadyPresentException("Contact Already Exists");
        }
    }
}
