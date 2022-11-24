package com.example.phonems.frontEnd;

import com.example.phonems.entity.Contact;
import com.example.phonems.service.IService;
import com.example.phonems.service.ServiceImpl;

public class FrontEnd {


    IService service = new ServiceImpl();

    public void display(){
        Contact contact = new Contact();
        contact.setPhoneNumber(9699310807l);
        contact.setEmailId("kaushik@gmail.com");
        contact.setLastName("bankar");
        contact.setFirstName("kaushik");
        service.addContact(contact);
        System.out.println(service.searchContactByGivenPhoneNo(9699310807l));
        System.out.println(service.displayAll());
    }
}
