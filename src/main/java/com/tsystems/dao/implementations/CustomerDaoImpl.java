package com.tsystems.dao.implementations;

import com.tsystems.dao.interfaces.CustomerDao;
import com.tsystems.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer, Integer> implements CustomerDao {

    @Override
    public void add(Customer customer) {
        em.merge(customer);
    }

    @Override
    public Customer getById(int id) {
        return em.find(Customer.class,id);
    }

    @Override
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Override
    public void delete(Customer customer) {
        em.remove(customer);
    }

    @Override
    public List<Customer> getAll() {
        return em.createQuery("SELECT NEW Customer(c.name, c.lastname, c.email, c.isBlocked) FROM Customer c", Customer.class).getResultList();
    }
}
