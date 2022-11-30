package com.example.phonems.service;

import com.example.phonems.entity.Contact;
import com.example.phonems.exceptions.EnterValidDataException;
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
    public String addContact(Contact contact) {
        Contact contactDetails = new Contact();
        if (uniqueCheck(contact.getPhoneNumber()))
            throw new ContactNotFoundException("Contact already present");

        if (!validation.validateFirstName(contact.getFirstName()))
            throw new EnterValidDataException("Enter valid Name");
        contactDetails.setFirstName(contact.getFirstName());

        if (!validation.validateLastName(contact.getLastName()))
            throw new EnterValidDataException("Enter valid Lastname");
        contactDetails.setLastName(contact.getLastName());

        if (!validation.validateEmail(contact.getEmailId()))
            throw new EnterValidDataException("Enter valid Email");
        contactDetails.setEmailId(contact.getEmailId());

        if (!validation.validateNumber(contact.getPhoneNumber()))
            throw new EnterValidDataException("Enter valid Number ");
        contactDetails.setPhoneNumber(contact.getPhoneNumber());

        if (!validation.validateAge(contact.getAge()))
            throw new EnterValidDataException("Enter valid Age");
        contactDetails.setAge(contact.getAge());

        contactList.add(contactDetails);
        logger.info("Contact added.");
        return "Contact added.";
    }

    @Override
    public List<Contact> displayAll() {
        if (contactList.size() == 0)
            throw new ContactNotFoundException("No contacts present in list");
        logger.info("Contact list displayed.");
        return contactList;
    }

    @Override
    public Contact searchContactByGivenPhoneNo(long number) {
        if (!validation.validateNumber(number))
            throw new EnterValidDataException("Enter valid number");
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber() == number) {
                logger.info("Found Contact");
                return contact;
            }
        }
        throw new ContactNotFoundException("Contact not present");
    }

    @Override
    public List<Contact> searchContactByFirstName(String firstName) {
        List<Contact> result = new ArrayList<>();
        if (!validation.validateFirstName(firstName))
            throw new EnterValidDataException("Enter valid name");
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
    public String removeContact(long phoneNumber) throws Exception {
        String string = String.valueOf(phoneNumber);
        String result = null;
        Contact contact1 = new Contact();
        if (string.length() != 10) {
            throw new EnterValidDataException("enter valid phone number");
        }
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber() == phoneNumber) {
                contact1 = contact;
                result = "found..";
            }
        }
        if (result == null) {
            throw new ContactNotFoundException("number not found");
        }

        contactList.remove(contact1);
        logger.info("contact deleted.");
        return phoneNumber+" :contact deleted.";
    }

    @Override
    public Contact updateEmail(long phoneNumber, String email) {
        String string = String.valueOf(phoneNumber);
        if (string.length() != 10)
            throw new EnterValidDataException("Enter valid number");
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber() == phoneNumber) {
                if (validation.validateEmail(email)) {
                    contact.setEmailId(email);
                    logger.info("Email updated successfully");
                    return contact;
                }
                throw new EnterValidDataException("Enter valid Email");
            }
        }
        throw new ContactNotFoundException("Contact not found.");
    }

    public boolean uniqueCheck(long number) {
        for (Contact contact : getContactList()) {
            if (contact.getPhoneNumber() == number)
                return true;
        }
        return false;
    }
}
