package com.tsystems.repository;

import com.tsystems.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nikita on 13.09.2020.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
