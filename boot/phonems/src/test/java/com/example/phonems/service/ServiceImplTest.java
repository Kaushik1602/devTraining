package com.example.phonems.service;

import com.example.phonems.entity.Contact;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceImplTest {

    ServiceImpl service;

    @Test
    void getContactList() {
    }

    @Test
    void addContact() {
        Contact contact = new Contact();
        contact.setAge(22);
        contact.setPhoneNumber(9699310807L);
        contact.setEmailId("bankar@gmail.com");
        contact.setFirstName("kausik");
        contact.setLastName("bankar");
        service = new ServiceImpl();
        assertEquals(contact,service.addContact(contact));
    }



    @Test
    void searchContactByGivenPhoneNo() {
    }

    @Test
    void searchContactByFirstName() {
    }

    @Test
    void removeContact() {
    }

    @Test
    void updateEmail() {
    }

    @Test
    void uniqueCheck() {
    }
}