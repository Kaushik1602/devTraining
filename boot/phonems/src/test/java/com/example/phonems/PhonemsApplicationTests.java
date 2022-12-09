package com.example.phonems;

import com.example.phonems.entity.Contact;
import com.example.phonems.exceptions.ContactAlreadyPresentException;
import com.example.phonems.exceptions.EnterValidDataException;
import com.example.phonems.service.IService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PhonemsApplicationTests {

 /*   @Autowired
    Contact contact;*/
    @Autowired
    IService service;

    Contact contact;
    @BeforeEach
    public void setUp(){
        contact = new Contact();
        contact.setAge(22);
        contact.setPhoneNumber(9699310807L);
        contact.setEmailId("bankar@gmail.com");
        contact.setFirstName("kausik");
        contact.setLastName("bankar");
    }

    @Test
    @Order(1)
    void testAddContact() {
        Contact actual = service.addContact(contact);
        Contact expected = service.searchContactByGivenPhoneNo(contact.getPhoneNumber());
        assertEquals(expected,actual);
    }

    @Test
    @Order(2)
    void testContactAlreadyPresentForAdd(){
        contact.setPhoneNumber(9999999999L);
        assertThrows(ContactAlreadyPresentException.class,()->service.addContact(contact),
                "Contact Already Exists");
    }

    @Test
    void testInvalidAgeForAddContact(){
        contact.setAge(2);
        contact.setPhoneNumber(7888888888L);
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "Age must be atleast 18 and it can't be null");
        contact.setAge(0);
        contact.setPhoneNumber(7889888888L);
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "Age must be atleast 18 and it can't be null");
        contact.setAge(-18);
        contact.setPhoneNumber(7889888888L);
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "Age must be atleast 18 and it can't be null");

    }

    @Test
    void testInvalidNumberForAddContact(){
        contact.setPhoneNumber(96993120807L);
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "Number must be 10 digit starting with 9,8,7 or 6 and it cannot be null");

    }


    @Test
    void testInvalidDataForAddContact(){
        contact.setPhoneNumber(999L);
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "Number must be 10 digit starting with 9,8,7 or 6 and it cannot be null");
        contact.setPhoneNumber(9699310807L);
        contact.setAge(2);
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "Age must be atleast 18 and it can't be null");
        contact.setAge(22);
        contact.setFirstName("ka");
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "First Name must be between 3 to 19 characters and " +
                        "it can only contain alphabets and it can't be null");
        contact.setFirstName("kaush");
        contact.setLastName("ba");
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "First Name must be between 3 to 19 characters and " +
                        "it can only contain alphabets and it can't be null");
        contact.setLastName("bankar");
        contact.setEmailId("kaus-$$@gmail.ssss");
        assertThrows(EnterValidDataException.class,()->service.addContact(contact),
                "Enter valid Email");

    }



}
