package com.example.phonems;

import com.example.phonems.controllers.Controller;
import com.example.phonems.entity.Contact;
import com.example.phonems.exceptions.ContactAlreadyPresentException;
import com.example.phonems.exceptions.ContactNotFoundException;
import com.example.phonems.exceptions.EnterValidDataException;
import com.example.phonems.service.IService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class PhonemsApplicationTests {

    @Autowired
    IService service;

    Contact contact;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	Controller controller;
    @BeforeEach
    public void setUp(){
        contact = new Contact();
        contact.setAge(22);
        contact.setPhoneNumber(9699310807L);
        contact.setEmailId("bankar@gmail.com");
        contact.setFirstName("kaushik");
        contact.setLastName("bankar");
    }

    @Test
    @Order(2)
    void testAddContact() {
        Contact actual = service.addContact(contact);
        Contact expected = service.searchContactByGivenPhoneNo(contact.getPhoneNumber());
        assertEquals(expected,actual);
    }

	@Test
	@Order(3)
	void testContactAlreadyPresentForAdd(){
		contact.setPhoneNumber(9999999999L);
		service.addContact(contact);
		assertThrows(ContactAlreadyPresentException.class,()->service.addContact(contact),
				"Contact Already Exists");
	}


	@Test
	void testInvalidDataForAddContact(){
		contact.setPhoneNumber(999L);
		assertThrows(EnterValidDataException.class,()->service.addContact(contact),
				"Number must be 10 digit starting with 9,8,7 or 6 and it cannot be null");
		contact.setPhoneNumber(9699300807L);
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

	@Test
	void testViewAll(){
		contact.setPhoneNumber(8989898989L);
		service.addContact(contact);
		List<Contact> expectedList = service.displayAll();
		List<Contact> actualList = service.displayAll();
		assertEquals(expectedList,actualList);
	}

	@Test
	@Order(1)
	void testContactNotFoundForViewAll(){
		assertThrows(ContactNotFoundException.class,()->service.displayAll());
	}

	@Test
	void testsearchContactByGivenPhoneNo(){
		contact.setPhoneNumber(9699310807L);
		Contact expected = service.searchContactByGivenPhoneNo(contact.getPhoneNumber());
		Contact actual = service.searchContactByGivenPhoneNo(expected.getPhoneNumber());
		assertEquals(expected, actual);
		assertThrows(ContactNotFoundException.class, () -> service.searchContactByGivenPhoneNo(9890906537L), "Contact not present");
	}

	@Test
	void testsearchContactByFirstName() {
		contact.setFirstName("kaushik");
//      Contact expected=service.searchContactByFirstName(contact.getFirstName());
		List<Contact> expected = service.searchContactByFirstName(contact.getFirstName());
		List<Contact> actual = service.searchContactByFirstName(contact.getFirstName());
		assertEquals(expected, actual);
		assertThrows(ContactNotFoundException.class, () -> service.searchContactByFirstName("chetan"),
				"No contacts found");
	}

	@Test
	void testUpdateContact(){
		contact.setPhoneNumber(9998887776L);
		service.addContact(contact);
		contact.setEmailId("kaushik@gmail.com");
		Contact actual = service.updateContact(contact.getPhoneNumber(),contact);
		Contact expected = service.searchContactByGivenPhoneNo(contact.getPhoneNumber());
		assertEquals(expected,actual);
		assertThrows(ContactNotFoundException.class,()->service.updateContact(9090909090L,contact),
				"Contact Not Present");
		assertThrows(EnterValidDataException.class,()->service.updateContact(909090990L,contact),
				"Number must be 10 digit starting with 9,8,7 or 6 and it cannot be null");
	}

	@Test
	void testRemoveContact(){
		contact.setPhoneNumber(9090000099L);
		service.addContact(contact);
		String expected = service.searchContactByGivenPhoneNo(contact.getPhoneNumber())+" :contact deleted.";
		String actual = service.removeContact(contact.getPhoneNumber());
		assertEquals(expected,actual);
		assertThrows(EnterValidDataException.class,()->service.removeContact(90909090909L));
		assertThrows(ContactNotFoundException.class,()->service.removeContact(9991234567L));
	}

	@Test
	void testControllerAddContact() throws Exception {
		String body = "{\n" +
				"\"firstName\": \"first\",\n" +
				"\"lastName\": \"second\",\n" +
				"\"phoneNumber\": 8889888899,\n" +
				"\"emailId\": \"abc@gmail.com\",\n" +
				"\"age\":89\n" +
				"    }";
		this.mockMvc.perform(post("/contacts/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isCreated());
	}

	@Test
	void testControllerViewAll() throws Exception {
		contact.setPhoneNumber(9998889998L);
		service.addContact(contact);
		this.mockMvc.perform(get("/contacts/view")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testControllerDelete() throws Exception {
		contact.setPhoneNumber(9900990099L);
		service.addContact(contact);
		this.mockMvc.perform(delete("/contacts/delete/9900990099"))
				.andExpect(status().isAccepted());
	}


}
