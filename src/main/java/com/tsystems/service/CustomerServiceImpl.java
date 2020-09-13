package com.tsystems.service;

import com.tsystems.dao.CustomerDao;
import com.tsystems.dao.CustomerDaoImpl;
import com.tsystems.dto.CustomerDto;
import com.tsystems.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * Created by nikita on 07.09.2020.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper mapper;
    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerMapper mapper, CustomerDao customerDao) {
        this.mapper = mapper;
        this.customerDao = customerDao;

    }


    @Override
    public void addCustomer(CustomerDto customerDto) {
        customerDao.add(mapper.convertToEntity(customerDto));
    }

    @Override
    public CustomerDto getById(int id) {
        return mapper.convertToDto(customerDao.getById(id));
    }

    @Override
    public void update(CustomerDto customerDto) {
        customerDao.update(mapper.convertToEntity(customerDto));
    }

    @Override
    public void delete(CustomerDto customerDto) {
        customerDao.delete(mapper.convertToEntity(customerDto));

    }
}
