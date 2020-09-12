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

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;

    @Override
    public void addCustomer(CustomerDto customerDto) {
        repository.save(mapper.convertToEntity(customerDto));
    }

    @Override
    public CustomerDto getById(int id) {
        return mapper.convertToDto(repository.getOne(id));
    }

    @Override
    public void update(CustomerDto customerDto) {
        repository.save(mapper.convertToEntity(customerDto));
    }

    @Override
    public void delete(CustomerDto customerDto) {
        repository.delete(mapper.convertToEntity(customerDto));

    }
}
