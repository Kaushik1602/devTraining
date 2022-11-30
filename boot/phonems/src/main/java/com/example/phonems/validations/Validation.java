package com.example.phonems.validations;

import com.example.phonems.exceptions.EnterValidDataException;
import com.example.phonems.service.IService;
import com.example.phonems.service.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validation {

    public void validateFirstName(String firstName) {
        if (firstName.length() < 3)
            throw new EnterValidDataException("Enter valid First Name");
    }

    public void validateLastName(String lastName) {
        if (lastName.length() < 3)
            throw new EnterValidDataException("Enter valid Last name");
    }

    public void validateNumber(long number) {
        String numStr = String.valueOf(number);
        if (numStr.length() != 10)
            throw new EnterValidDataException("Enter valid number");

    }

    public void validateEmail(String email) {
        Pattern validation = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        if (!(validation.matcher(email).matches() && email.length() != 0))
            throw new EnterValidDataException("Enter valid Email");
    }

    public void validateAge(int age) {
        if(age < 1)
            throw new EnterValidDataException("Enter valid Age");
    }


}
