package com.tsystems.service;

import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
@Service
public interface CustomerService {

    void addCustomer(CustomerDto customerDto);

    CustomerDto getById(int id);

    void update(CustomerDto customerDto);

    void delete(CustomerDto customerDto);

}
