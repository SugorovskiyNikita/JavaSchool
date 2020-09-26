package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.CustomerDao;
import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Contract;
import com.tsystems.entities.Customer;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.util.PassGen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by nikita on 07.09.2020.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private  CustomerDao customerDao;


    @Override
    public CustomerDto add(CustomerDto customerDto) {
        /*The password will be generated automatically
         */
        Customer customer = customerDto.convertToEntity();
        customer.setIsBlocked(0);
        customer.setPassword("password");
        return new CustomerDto(customerDao.add(customer));
    }

    @Override
    public void remove(Integer key) {
        customerDao.remove(key);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<CustomerDto> loadAll() {
        return customerDao.loadAll().stream()
                .map(e -> new CustomerDto(e).addDependencies(e))
                .collect(Collectors.toList());
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public CustomerDto loadByKey(Integer key) {
        Customer customer = customerDao.loadByKey(key);
        return new CustomerDto(customer).addDependencies(customer);
    }
}
