package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.ContractDao;
import com.tsystems.db.dao.interfaces.CustomerDao;
import com.tsystems.db.dto.ContractDto;
import com.tsystems.db.entities.Contract;
import com.tsystems.db.entities.Customer;
import com.tsystems.db.entities.Option;
import com.tsystems.db.entities.Tariff;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by nikita on 07.11.2020.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractServiceImplTest {

    @Mock
    private CustomerDao customerDao;

    @Mock
    private ContractDao contractDao;

    @Test
    public void test() throws Exception{
        Assert.assertNotNull(contractService);
    }

    @InjectMocks
    private ContractServiceImpl contractService;

    public Contract contract;

    @BeforeEach
    void onSetUp() {
        MockitoAnnotations.initMocks(this);
        Customer customer = new Customer();
        customer.setId(2);
        Tariff tariff = new Tariff();
        tariff.setId(3);
        Option option1 = new Option();
        option1.setId(4);
        Option option2 = new Option();
        option2.setId(5);
        Set<Option> options = new HashSet<>();
        options.add(option1);
        options.add(option2);
        contract = Contract.builder()
                .id(1)
                .balance(new BigDecimal(100))
                .customer(customer)
                .isBlocked(0)
                .number("+79811447779")
                .tariff(tariff)
                .usedOptions(options)
                .build();
    }

    @Test
    public void testAddNew() {
        Customer customer = new Customer();
        customer.setId(3);
        when(customerDao.loadByKey(3)).thenReturn(customer);
        ContractDto contractDto = contractService.addNew(3);
        Assert.assertNotNull("Contract successfully created",contractDto);
    }

    @Test
    public void testSetBlock() throws Exception {
        Contract contract = new Contract();
        contract.setId(1);
        Integer blockLevel = 1;
        contract.setIsBlocked(blockLevel);
        when(contractDao.loadByKey(anyInt())).thenReturn(contract);
        ContractDto contractDto = contractService.setBlock(1, 0);
        Assert.assertEquals("BlockLevel changed", 0, (int) contractDto.getIsBlocked());
    }

    @Test
    public void testLoadByKey() {
        Contract contract = new Contract();
        contract.setId(5);
        when(contractDao.loadByKey(anyInt())).thenReturn(contract);
        ContractDto contractDto = contractService.loadByKey(5);
        Assert.assertEquals("Contract successfully uploaded", 5, (int) contractDto.getId());

    }

    @Test
    public void testLoadAll() {
        Contract contract = new Contract();
        Contract contract1 = new Contract();
        contract.setId(1);
        contract1.setId(2);
        List<Contract> contracts = new ArrayList<>();
        contracts.add(contract);
        contracts.add(contract1);
        when(contractDao.loadAll()).thenReturn(contracts);
        List<ContractDto> contractDtos = contractService.loadAll();
        Assert.assertNotNull("Contract list successfully uploaded", contractDtos);
    }

    @Test
    public void testRemove() {
        contractService.remove(1);
        verify(contractDao, atLeastOnce()).remove(any());
    }

}