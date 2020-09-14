package com.tsystems.services.interfaces;

import com.tsystems.entities.Customer;
import org.springframework.stereotype.Service;

/**
 * Created by nikita on 07.09.2020.
 */
@Service
public interface CustomerService extends GenericService<Customer>{

    void add(Customer customer);

    Customer getById(int id);

    void update(Customer customer);

    void delete(Customer customer);
}
