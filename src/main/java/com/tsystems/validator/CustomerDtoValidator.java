package com.tsystems.validator;

import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Customer;
import com.tsystems.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Created by nikita on 01.10.2020.
 */
public class CustomerDtoValidator implements Validator {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerDto customerDto = (CustomerDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (customerDto.getName().length() < 1 || customerDto.getName().length() > 30) {
            errors.rejectValue("name", "Size.customer.name");
        }
        Pattern pattern = Pattern.compile("[A-z]+$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(customerDto.getName()).matches())) {
            errors.rejectValue("name", "Invalid.customer.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Required");
        if (customerDto.getSurname().length() < 1 || customerDto.getName().length() > 40) {
            errors.rejectValue("surname", "Size.customer.surname");
        }
        if (!(pattern.matcher(customerDto.getSurname()).matches())) {
            errors.rejectValue("surname", "Invalid.customer.surname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "Required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"passportNumber", "Required");
        if (customerDto.getPassportNumber().length() != 10) {
            errors.rejectValue("passportNumber", "Format.customer.passportNumber");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"passportData", "Required");
        if (customerDto.getPassportNumber().length() < 20) {
            errors.rejectValue("passportData", "Format.customer.passportData");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "Required");
        if (customerDto.getAddress().length() < 20 || customerDto.getAddress().length() > 250) {
            errors.rejectValue("address", "Size.customer.address");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (customerService.findByEmail(customerDto.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.contract.email");
        }

    }
}
