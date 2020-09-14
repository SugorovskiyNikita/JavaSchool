package com.tsystems.dao.interfaces;

import com.tsystems.entities.Customer;
import org.springframework.stereotype.Repository;

/**
 * Created by nikita on 07.09.2020.
 */
@Repository
public interface CustomerDao extends GenericDao<Customer, Integer> {

}
