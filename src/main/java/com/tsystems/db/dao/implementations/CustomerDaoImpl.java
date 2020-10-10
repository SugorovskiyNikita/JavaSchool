package com.tsystems.db.dao.implementations;

import com.tsystems.db.dao.interfaces.CustomerDao;
import com.tsystems.db.entities.Customer;
import com.tsystems.db.entities.Customer_;
import org.springframework.stereotype.Repository;

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

    /*@Override
    public Customer findByEmail(String email) {
        Customer customer = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class)
                .setParameter("email", email).getSingleResult();
        return customer;
    }*/

    @Override
    public Customer findByEmail(String email) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get(Customer_.email), email));
        TypedQuery<Customer> selectByLogin = em.createQuery(criteriaQuery);

        Customer customer = selectByLogin.getSingleResult();
        return customer;


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
        return em.createQuery("SELECT t FROM Customer t", Customer.class)
                .getResultList();
    }
}
