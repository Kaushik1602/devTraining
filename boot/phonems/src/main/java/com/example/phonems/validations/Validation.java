package com.example.phonems.validations;

import com.example.phonems.exceptions.EnterValidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validation {

    Logger logger = LoggerFactory.getLogger(Validation.class);

    public String validateFirstName(String firstName) {
        if (firstName == null || firstName.length() < 3 || firstName.length() > 20 || !firstName.matches("[a-zA-Z]+")) {
            logger.warn("First name format invalid");
            throw new EnterValidDataException("First Name must be between 3 to 19 characters and it can only contain alphabets and it can't be null");
        }
        return firstName;
    }

    public String validateLastName(String lastName) {
        if (lastName == null || lastName.length() < 3 || lastName.length() > 20 || !lastName.matches("[a-zA-Z]+")) {
            logger.warn("Last name format invalid");
            throw new EnterValidDataException("Last Name must be between 3 to 19 characters and it can only contain alphabets and it can't be null");
        }
        return lastName;
    }

    public long validateNumber(Long number) {
        String numStr = String.valueOf(number);
        String firstChar = numStr.substring(0, 1);
        String checkNums = "9876";
        if (numStr.length() != 10 || !checkNums.contains(firstChar) || !numStr.matches("[0-9]+")) {
            logger.warn("Number format invalid");
            throw new EnterValidDataException("Number must be 10 digit starting with 9,8,7 or 6 and it cannot be null");
        }
        return number;
    }

    public String validateEmail(String email) {
        Pattern validation = Pattern.compile("^[\\w-\\.]+@([a-zA-Z]+\\.)+[a-zA-Z]{2,4}$");
        if (email == null || !(validation.matcher(email).matches() && email.length() != 0)) {
            logger.warn("Email format invalid");
            throw new EnterValidDataException("Enter valid Email");
        }
        return email;
    }

    public int validateAge(Integer age) {
        if (age < 17) {
            logger.warn("Age Invalid");
            throw new EnterValidDataException("Age must be atleast 18 and it can't be null");
        }
        return age;
    }


}
