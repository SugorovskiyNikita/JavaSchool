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
    public Customer findByEmail(String email) {
        return em.find(Customer.class, email);
    }

    @Override
    public Customer add(Customer customer) {
        return em.merge(customer);
    }

    @Override
    public Customer loadByKey(Integer key) {
        return em.find(Customer.class,key);
    }

    @Override
    public void remove(Integer customer) {
        em.remove(customer);
    }

    @Override
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Override
    public List<Customer> loadAll() {
        return em.createQuery("SELECT NEW Customer(c.id, c.name, c.surname, c.email, c.isBlocked) FROM Customer c", Customer.class)
                .getResultList();
    }
}
