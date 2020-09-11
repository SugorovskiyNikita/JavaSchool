package com.tsystems.service;

import com.tsystems.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
@Service
public interface CustomerService {

    void addCustomer(Customer customer);

    Customer getById(int id);

    void update(Customer customer);

    void delete(Customer customer);

}
