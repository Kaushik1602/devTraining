package com.example.phonems.validations;

import com.example.phonems.entity.Contact;
import com.example.phonems.service.IService;
import com.example.phonems.service.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class Validation {

    IService service = new ServiceImpl();


    public boolean validateFirstName(String firstName){
        return firstName.length() > 4;
    }

    public boolean validateLastName(String lastName){
        return lastName.length() > 4;
    }

    public boolean validateNumber(long number){
        String numStr = String.valueOf(number);
        List<Long> numbers = numbers();
        System.out.println(numbers);
        return numStr.length() == 10 && !numbers.contains(number);
    }

    public boolean validateEmail(String email){
        Pattern validation = Pattern.compile("[a-zA-Z0-9@\\.\\-\\_]+");
        return validation.matcher(email).matches() && email.length() != 0;
    }

    public boolean validateAge(int age){
        return age>0;
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
