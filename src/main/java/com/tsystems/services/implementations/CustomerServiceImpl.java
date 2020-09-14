package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.CustomerDao;
import com.tsystems.dao.implementations.CustomerDaoImpl;
import com.tsystems.entities.Customer;
import com.tsystems.services.interfaces.CustomerService;
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
    private CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void add(Customer customer) {
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
