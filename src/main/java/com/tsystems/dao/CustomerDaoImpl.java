package com.tsystems.dao;

import com.tsystems.entities.Customer;
import org.springframework.stereotype.Repository;


/**
 * Created by nikita on 07.09.2020.
 */
@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao  {

    @Override
    public void add(Customer customer) {
        em.merge(customer);
    }

    @Override
    public Customer getById(int id) {
        return (Customer) em.find(entityClass,id);
    }

    @Override
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Override
    public void delete(Customer customer) {
        em.remove(customer);
    }
}
