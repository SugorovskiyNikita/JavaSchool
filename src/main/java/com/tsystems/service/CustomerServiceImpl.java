package com.tsystems.service;

import com.tsystems.dao.CustomerDao;
import com.tsystems.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
