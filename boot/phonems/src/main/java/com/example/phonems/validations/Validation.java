package com.example.phonems.validations;

import com.example.phonems.service.IService;
import com.example.phonems.service.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validation {

    IService service = new ServiceImpl();


    public boolean validateFirstName(String firstName) {
        return firstName.length() > 2;
    }

    public boolean validateLastName(String lastName) {
        return lastName.length() > 2;
    }

    public boolean validateNumber(long number) {
        String numStr = String.valueOf(number);
        return numStr.length() == 10;
    }

    public boolean validateEmail(String email) {
        Pattern validation = Pattern.compile("[a-zA-Z0-9@\\.\\-\\_]+");
        return validation.matcher(email).matches() && email.length() != 0;
    }

    public boolean validateAge(int age) {
        return age > 0;
    }


}
