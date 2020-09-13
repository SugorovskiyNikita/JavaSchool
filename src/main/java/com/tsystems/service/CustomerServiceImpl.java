package com.tsystems.service;

import com.tsystems.dao.CustomerDao;
import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * Created by nikita on 07.09.2020.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper mapper;
    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(ModelMapper mapper, CustomerDao customerDao) {
        this.mapper = mapper;
        this.customerDao = customerDao;

    }

    @Override
    public void addCustomer(CustomerDto customerDto) {
        customerDao.add(mapper.map(customerDto, Customer.class));
    }

    @Override
    public Customer getById(int id) {
        return customerDao.getById(id);
    }

    @Override
    public void update(CustomerDto customerDto) {
        customerDao.update(mapper.map(customerDto, Customer.class));
    }

    @Override
    public void delete(CustomerDto customerDto) {
        customerDao.delete(mapper.map(customerDto, Customer.class));

    }
}
