package com.tsystems.validator;

import com.tsystems.entities.Contract;
import com.tsystems.services.interfaces.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by nikita on 03.10.2020.
 */
public class ContractValidator implements Validator {

    @Autowired
    ContractService contractService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Contract.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
