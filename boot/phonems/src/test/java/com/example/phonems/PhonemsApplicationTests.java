package com.example.phonems;

import com.example.phonems.entity.Contact;
import com.example.phonems.exceptions.ContactAlreadyPresentException;
import com.example.phonems.exceptions.EnterValidDataException;
import com.example.phonems.service.IService;
import com.example.phonems.service.ServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PhonemsApplicationTests {

    Contact contact;

    IService service;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        contact = new Contact();
        service = new ServiceImpl();
    }
 /*   @Autowired
    Contact contact;*/


    @Test
    void testAddContact() {

        contact.setAge(22);
        contact.setPhoneNumber(9699310807L);
        contact.setEmailId("bankar@gmail.com");
        contact.setFirstName("kausik");
        contact.setLastName("bankar");
        Contact actual = service.addContact(contact);
        Contact expected = service.searchContactByGivenPhoneNo(contact.getPhoneNumber());
        assertEquals(expected,actual);
    }

    @Test
    void testContactAlreadyPresentForAdd(){
        Contact contact = new Contact();
        contact.setAge(22);
        contact.setPhoneNumber(9699310807L);
        contact.setEmailId("bankar@gmail.com");
        contact.setFirstName("kausik");
        contact.setLastName("bankar");
        Contact result = service.addContact(contact);
        assertThrows(ContactAlreadyPresentException.class,()->service.addContact(contact),
                "Contact Already Exists");
    }

    @Test
    void testInvalidAgeForAddContact(){
        contact.setAge(2);
        contact.setPhoneNumber(9699320807L);
        contact.setEmailId("bankar@gmail.com");
        contact.setFirstName("kausik");
        contact.setLastName("bankar");
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "Age must be atleast 18 and it can't be null");

    }

    @Test
    void testInvalidNumberForAddContact(){
  //      Contact contact = new Contact();
        contact.setAge(22);
        contact.setPhoneNumber(96993120807L);
        contact.setEmailId("bankar@gmail.com");
        contact.setFirstName("kausik");
        contact.setLastName("bankar");
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "Number must be 10 digit starting with 9,8,7 or 6 and it cannot be null");
    }



}
