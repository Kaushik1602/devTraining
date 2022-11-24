package com.example.phonems.validations;

import com.example.phonems.entity.Contact;
import com.example.phonems.service.IService;
import com.example.phonems.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class Validation {

    IService service = new ServiceImpl();


    public boolean validateFirstName(String firstName){
        if (firstName.length()<4)
            return false;
        return true;
    }

    public boolean validateLastName(String lastName){
        if (lastName.length()<4)
            return false;
        return true;
    }

    public boolean validateNumber(long number){
        String numStr = String.valueOf(number);
        List<Long> numbers = numbers();
        System.out.println(numbers);
        if (numStr.length()!=10 || numbers.contains(number)) {
            return false;
        }
        return true;
    }

    public boolean validateEmail(String email){
        Pattern validation = Pattern.compile("[a-zA-Z0-9@\\.\\-\\_]+");
        if (validation.matcher(email).matches()&&email.length()!=0)
            return true;
        return false;
    }

    public List<Long> numbers(){
        List<Long> numbers = new ArrayList<>();
        List<Contact> contacts = service.displayAll();
        System.out.println(contacts);
        for (Contact c:contacts){
            numbers.add(c.getPhoneNumber());
        }
        return numbers;
    }
}
