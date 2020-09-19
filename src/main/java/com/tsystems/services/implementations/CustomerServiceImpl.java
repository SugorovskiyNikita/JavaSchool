package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.CustomerDao;
import com.tsystems.dao.implementations.CustomerDaoImpl;
import com.tsystems.entities.Customer;
import com.tsystems.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by nikita on 07.09.2020.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {


    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void add(Customer customer) {
        customerDao.add(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void remove(Customer customer) {
        customerDao.remove(customer);
    }

    @Override
    public List<Customer> loadAll() {
        return customerDao.loadAll();
    }

    @Override
    public Customer loadByKey(Integer key) {
        return customerDao.loadByKey(key);
    }
}
