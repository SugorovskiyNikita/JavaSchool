package com.tsystems.service;

import com.tsystems.dao.CustomerDao;
import com.tsystems.dao.CustomerDaoImpl;
import com.tsystems.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by nikita on 07.09.2020.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao = new CustomerDaoImpl();


    @Override
    public void addCustomer(Customer customer) {
        customerDao.add(customer);
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
