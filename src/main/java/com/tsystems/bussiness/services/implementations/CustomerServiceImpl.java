package com.tsystems.bussiness.services.implementations;

import com.tsystems.db.dao.interfaces.CustomerDao;
import com.tsystems.db.dao.interfaces.RoleDao;
import com.tsystems.db.dto.ContractDto;
import com.tsystems.db.dto.CustomerDto;
import com.tsystems.db.dto.RoleDto;
import com.tsystems.db.entities.Contract;
import com.tsystems.db.entities.Customer;
import com.tsystems.db.entities.Role;
import com.tsystems.bussiness.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;


    @Override
    public CustomerDto add(CustomerDto customerDto) {
        /*The password will be generated automatically
         */
        Customer customer = customerDto.convertToEntity();
        Contract contract = new ContractDto().convertToEntity();
        contract.setCustomer(customer);
        contract.setBalance(new BigDecimal("100.00"));
        customer.setIsBlocked(0);
        contract.setIsBlocked(0);

        // Add contracts to customer
        Set<Contract> contracts = new HashSet<>();
        contracts.add(contract);
        customer.setContracts(contracts);

        // NO PASSWORD. Password is created by user with first login
        //customer.setPassword("password");
        customer.setPassword(passwordEncoder.encode("password"));

        //Give role, default "USER"
        Role role = new RoleDto().convertToEntity();
        role.setId(1);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        customer.setRoles(roles);



        return new CustomerDto(customerDao.add(customer));
    }

    @Override
    public void remove(Integer key) {
        customerDao.remove(key);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> loadAll() {
        return customerDao.loadAll().stream()
                .map(e -> new CustomerDto(e).addDependencies(e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto loadByKey(Integer key) {
        Customer customer = customerDao.loadByKey(key);
        return new CustomerDto(customer).addDependencies(customer);
    }

    @Override
    public CustomerDto findByEmail(String email) {
        Customer customer = customerDao.findByEmail(email);
        return new CustomerDto(customer).addDependencies(customer);
    }
}
