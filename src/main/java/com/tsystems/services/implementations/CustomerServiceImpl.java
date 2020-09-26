package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.CustomerDao;
import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Customer;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.util.PassGen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by nikita on 07.09.2020.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    public final ModelMapper mapper;

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, ModelMapper mapper) {
        this.customerDao = customerDao;
        this.mapper = mapper;
    }

    @Override
    public void add(CustomerDto customerDto) {
        /*The password will be generated automatically
        and sent to the user's email.
         */
        String password = new PassGen().randomPass();
        //passwordEncoder.encode(password);
        customerDto.setIsBlocked(0);
        customerDto.setPassword(password);
        customerDao.add(mapper.map(customerDto, Customer.class));
    }

    @Override
    public void update(CustomerDto customerDto) {
        customerDao.update(mapper.map(customerDto, Customer.class));
    }

    @Override
    public void remove(CustomerDto customerDto) {
        customerDao.remove(mapper.map(customerDto, Customer.class));
    }

    @Override
    public List<CustomerDto> loadAll() {
        return customerDao.loadAll().stream()
                .map(c -> new CustomerDto((c.getId()), c.getName(), c.getSurname(), c.getEmail(), c.getIsBlocked()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto loadByKey(Integer key) {
        Customer customer = customerDao.loadByKey(key);
        return mapper.map(customer, CustomerDto.class);
    }
}
