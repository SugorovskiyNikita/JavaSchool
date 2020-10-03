package com.tsystems.dao.implementations;

import com.tsystems.dao.interfaces.CustomerDao;
import com.tsystems.entities.Customer;
import com.tsystems.entities.Customer_;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer, Integer> implements CustomerDao {

    @Override
    public Customer findByEmail(String email) throws Exception {

        TypedQuery<Customer> selectByEmail = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class)
                .setParameter("email", email);

        Customer customer = selectByEmail.getSingleResult();
        if (customer != null) {
            return customer;
        } else throw new Exception("No user found bu given login");
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
