package com.tsystems.service;

import com.tsystems.dao.CustomerDao;
import com.tsystems.dao.CustomerDaoImpl;
import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Customer;
import com.tsystems.mapper.CustomerMapper;
import com.tsystems.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * Created by nikita on 07.09.2020.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Autowired
    private CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void addCustomer(CustomerDto customerDto) {
        mapper.convertToDto(repository.save(mapper.convertToEntity(customerDto)));
    }

    @Override
    public Customer getById(int id) {
        return customerDao.getById(id);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);

    }
}
