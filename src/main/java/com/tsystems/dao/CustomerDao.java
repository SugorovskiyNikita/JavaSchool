package com.tsystems.dao;

import com.tsystems.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
@Repository
public interface CustomerDao extends GenericDao<Customer> {

}
