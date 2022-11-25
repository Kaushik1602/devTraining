package com.example.phonems.service;

import com.example.phonems.entity.Contact;
import com.example.phonems.exceptions.EnterValidDataException;
import com.example.phonems.exceptions.NoNotFoundException;
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
    public String addContact(Contact contact) throws Exception {
        Contact contactDetails = new Contact();
        try {
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
                throw new EnterValidDataException("Enter valid Number " + contact.getPhoneNumber());

            contactDetails.setPhoneNumber(contact.getPhoneNumber());

            if (!validation.validateAge(contact.getAge()))
                throw new EnterValidDataException("Enter valid Age");
            contactDetails.setAge(contact.getAge());
        } catch (EnterValidDataException e) {
            return e.getMessage();
        }
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
    public String removeContact(long phoneNumber) throws Exception {
        String string = String.valueOf(phoneNumber);
        String result = null;
        Contact contact1=new Contact();
        try {
            if (string.length() != 10) {
                throw new EnterValidDataException("enter valid phone number");
            }
            for (Contact contact : contactList) {
                if (contact.getPhoneNumber() == phoneNumber) {
                    contact1=contact;
                    result = "found..";
                }
            }
            if (result == null) {
                throw new NoNotFoundException("number not found");
            }
        } catch (EnterValidDataException e) {
            return e.getMessage();
        } catch (NoNotFoundException e) {
            return e.getMessage();
        }
        contactList.remove(contact);
        return "contact removed successfully.";
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
