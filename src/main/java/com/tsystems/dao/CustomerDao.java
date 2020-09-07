package com.tsystems.dao;

import com.tsystems.entities.Customer;
import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
public interface CustomerDao {
    List<Customer> findAll();
}
