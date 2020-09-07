package com.tsystems.business;

import com.tsystems.business.services.implementations.CustomerServiceImpl;
import com.tsystems.business.services.interfeces.CustomerService;
import com.tsystems.db.entities.Customer;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class CustomerServiceImplTest {

    CustomerService service = new CustomerServiceImpl();

    @Test
        public void add() throws Exception {
            Customer customer = new Customer();
            customer.setAddress("address");
            customer.setDateOfBirth(new Date());
            customer.setEmail("test@test.ru");
            customer.setIsBlocked(0);
            customer.setName("Martin");
            customer.setLastname("Joy");
            customer.setPassportDate("789097980");
            customer.setPassword("5");

           service.addCustomer(customer);
        }

}


