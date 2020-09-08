package com.tsystems.dao;

import com.tsystems.entities.Customer;
import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
public interface CustomerDao {
    void addCustomer(Customer customer);

    Customer getById(int id);

    List<Customer> findAll();

    void update(Customer customer);

    void delete(int id);




}
