package com.tsystems.service;

import com.tsystems.entities.Customer;

import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
public interface CustomerService {
    List<Customer> findAll();
}
