package com.tsystems.dao;

import com.tsystems.entities.Customer;
import org.springframework.stereotype.Repository;


/**
 * Created by nikita on 07.09.2020.
 */
@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer>  {

    public CustomerDaoImpl() {
        super();
    }

    @Override
    public void add(Customer entity) {
        super.add(entity);
    }

    @Override
    public Customer getById(int id) {
        return super.getById(id);
    }

    @Override
    public void update(Customer entity) {
        super.update(entity);
    }

    @Override
    public void delete(Customer entity) {
        super.delete(entity);
    }
}
