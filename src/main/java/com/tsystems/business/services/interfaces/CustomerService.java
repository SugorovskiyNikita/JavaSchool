package com.tsystems.business.services.interfaces;

import com.tsystems.db.dto.CustomerDto;

/**
 * Created by nikita on 07.09.2020.
 */
public interface CustomerService extends GenericService<CustomerDto, Integer> {

    /**
     * Finding customer by email
     * @param email number to search
     * @return found contract DTO object
     */
    CustomerDto findByEmail(String email) throws Exception;

    /**
     * Add new customer
     * @param customerDto new customer
     * @return customer DTO object
     */
    CustomerDto add(CustomerDto customerDto);

}
