package com.tsystems.mapper;


import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nikita on 07.09.2020.
 */
@Component
public class CustomerMapper  {

    @Autowired
    ModelMapper modelMapper;

    public CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }

    public Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        return customer;
    }

}
