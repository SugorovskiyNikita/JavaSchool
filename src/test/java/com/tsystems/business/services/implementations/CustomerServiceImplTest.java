package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.ContractDao;
import com.tsystems.db.dao.interfaces.CustomerDao;
import com.tsystems.db.dao.interfaces.RoleDao;
import com.tsystems.db.dto.ContractDto;
import com.tsystems.db.dto.CustomerDto;
import com.tsystems.db.entities.Contract;
import com.tsystems.db.entities.Customer;
import com.tsystems.db.entities.Role;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by nikita on 09.11.2020.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerDao customerDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void onSetUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() throws Exception{
        Assert.assertNotNull(customerService);

    }

    @Test
    public void testAdd() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1);
        when(passwordEncoder.encode(any())).thenReturn("password");
        Role role = new Role();
        role.setId(1);
        when(roleDao.getRoleById(1)).thenReturn(role);
        when(customerDao.add(any(Customer.class))).thenReturn(customerDto.convertToEntity());
        CustomerDto customerDto1 = customerService.add(customerDto);
        Assert.assertNotNull("Customer successfully created",customerDto1);
    }

    @Test
    public void remove() {
        customerService.remove(1);
        verify(customerDao, atLeastOnce()).remove(any());
    }

    @Test
    public void testLoadAll() {
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        List<Customer> customers = new ArrayList<>();
        customer.setId(1);
        customer1.setId(2);
        customers.add(customer);
        customers.add(customer1);
        when(customerDao.loadAll()).thenReturn(customers);
        List<CustomerDto> customerDtos = customerService.loadAll();
        Assert.assertNotNull("Customer list successfully uploaded", customerDtos);
    }

    @Test
    public void testLoadByKey() {
        Customer customer = new Customer();
        customer.setId(5);
        when(customerDao.loadByKey(anyInt())).thenReturn(customer);
        CustomerDto customerDto = customerService.loadByKey(5);
        Assert.assertEquals("Customer successfully uploaded", 5, (int) customerDto.getId());
    }

    @Test
    public void testFindByEmail() {
        Customer customer = new Customer();
        customer.setId(4);
        customer.setEmail("gg@gmail.com");
        when(customerDao.findByEmail(any())).thenReturn(customer);
        CustomerDto customerDto = customerService.findByEmail("gg@gmail.com");
        Assert.assertEquals("Customer successfully find by email", 4, (int) customerDto.getId());
    }
}