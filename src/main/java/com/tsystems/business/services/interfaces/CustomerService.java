package com.tsystems.business.services.interfaces;

import com.tsystems.db.dto.CustomerDto;
import org.springframework.stereotype.Service;

/**
 * Created by nikita on 07.09.2020.
 */
@Service
public interface CustomerService extends GenericService<CustomerDto, Integer> {

    CustomerDto findByEmail(String email) throws Exception;

    CustomerDto setBlock(Integer id, Integer blockLevel);

}
