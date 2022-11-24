package com.example.phonems.service;

import com.example.phonems.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl implements IService{

    List<Contact> contactList = new ArrayList<>();

    @Override
    public Contact addContact(Contact contact) {
        Contact result = new Contact();
        result.setFirstName(contact.getFirstName());
        result.setLastName(contact.getLastName());
        result.setEmailId(contact.getEmailId());
        result.setPhoneNumber(contact.getPhoneNumber());
        contactList.add(result);
        return result;
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

   
}
