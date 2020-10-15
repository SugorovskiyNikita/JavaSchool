package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.CustomerDao;
import com.tsystems.db.dao.interfaces.RoleDao;
import com.tsystems.db.dto.ContractDto;
import com.tsystems.db.dto.CustomerDto;
import com.tsystems.db.entities.Contract;
import com.tsystems.db.entities.Customer;
import com.tsystems.db.entities.Role;
import com.tsystems.business.services.interfaces.CustomerService;
import org.apache.log4j.Logger;
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
    private CustomerDao customerDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);


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
        Role role = roleDao.getRoleById(1);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        customer.setRoles(roles);
        try {
            customerDao.add(customer);
            logger.info("New customer and new contract is created. Email = " + customer.getEmail());
        } catch (Exception e) {
            logger.warn("Customer" + customerDto.getEmail() + "error adding to the DB" + e);
        }
        return new CustomerDto(customer);

    }

    @Override
    public void remove(Integer key) {
        try {
            customerDao.remove(key);
            logger.info("Customer Id = " + key + " has been deleted.");
        } catch (Exception e) {
            logger.warn("Customer Id = " + key + " has not deleted." + e);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> loadAll() {
        logger.info("Loading all customers from the database.");
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

    @Override
    public CustomerDto setBlock(Integer id, Integer blockLevel) {
        Customer customer = customerDao.loadByKey(id);
        customer.setIsBlocked(blockLevel);
        logger.info("Customer block level has been changed. Customer id = " + customer.getId() + " block level = " + blockLevel);
        return new CustomerDto(customer);
    }
}
