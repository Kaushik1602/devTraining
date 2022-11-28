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

            if (uniqueCheck(contact.getPhoneNumber()))
                throw new ContactNotFoundException("Contact already present");

        } catch (EnterValidDataException e) {
            return e.getMessage();
        } catch (ContactNotFoundException e) {
            return e.getMessage();
        }
        contactList.add(contactDetails);
        return "Contact Added";
    }

    @Override
    public List<Contact> displayAll() {
        try {
            if (contactList.size()==0)
                throw new ContactNotFoundException("No contacts present in list");
        }catch (ContactNotFoundException e){
            logger.info(e.getMessage());
        }
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
        try {
            if (result==null)
                throw new ContactNotFoundException("Contact not present");
        }catch (ContactNotFoundException e){
            logger.info(e.getMessage());
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
        Contact contact1 = new Contact();
        try {
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
        } catch (EnterValidDataException e) {
            return e.getMessage();
        } catch (ContactNotFoundException e) {
            return e.getMessage();
        }
        contactList.remove(contact1);
        return "contact removed successfully.";
    }

    @Override
    public String updateEmail(long phoneNumber, String email) {
        String string = String.valueOf(phoneNumber);
        try {
            if (string.length()!=10)
                throw new EnterValidDataException("Enter valid number");
            for (Contact contact : contactList) {
                if (contact.getPhoneNumber() == phoneNumber) {
                    if (validation.validateEmail(email)) {
                        contact.setEmailId(email);
                        return "Email updated successfully";
                    }
                    throw new EnterValidDataException("Enter valid Email");
                }
            }
            throw new ContactNotFoundException("Contact not found.");
        } catch (EnterValidDataException e) {
            return e.getMessage();
        } catch (ContactNotFoundException e) {
            return e.getMessage();
        }

    }

    public boolean uniqueCheck(long number) {
        for (Contact contact : getContactList()) {
            if (contact.getPhoneNumber() == number)
                return true;
        }
        return false;
    }
}
