package com.tsystems.business.services.implementations;

import com.tsystems.business.services.interfeces.CustomerService;
import com.tsystems.db.entities.Customer;
import com.tsystems.db.implimentations.CustomerDaoImpl;
import com.tsystems.db.interfaces.CustomerDao;

/**
 * Created by nikita on 05.09.20.
 */
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void addCustomer(Customer customer) {
        customerDao.create(customer);
    }
}
