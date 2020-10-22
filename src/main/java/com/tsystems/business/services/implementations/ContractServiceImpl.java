package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.ContractDao;
import com.tsystems.db.dao.interfaces.CustomerDao;
import com.tsystems.db.dto.ContractDto;
import com.tsystems.db.entities.Contract;
import com.tsystems.db.entities.Option;
import com.tsystems.db.entities.Tariff;
import com.tsystems.business.services.interfaces.ContractService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nikita on 15.09.2020.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractDao contractDao;

    private final CustomerDao customerDao;

    private static Logger logger = Logger.getLogger(ContractServiceImpl.class);


    @Override
    public ContractDto addNew(Integer customerId) {
        // Create new contract. Default balance on new contract == 100 and non blocked
        Contract contract = new Contract();
        contract.setCustomer(customerDao.loadByKey(customerId));
        contract.setBalance(new BigDecimal("100.00"));
        contract.setIsBlocked(0);
        contractDao.add(contract);
        logger.info("New contract is created. Customer = " + customerId);
        return new ContractDto(contract);
    }

    @Override
    public ContractDto add(ContractDto entityDto) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractDto> loadAll() {
        logger.info("Loading all contracts from the database.");
        return contractDao
                .loadAll()
                .stream()
                .map(e -> new ContractDto(e).addDependencies(e))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Integer key) {
        contractDao.remove(key);
        logger.info("Contract was deleted. Id = " + key);
    }

    @Override
    public ContractDto setBlock(Integer id, Integer blockLevel) {
        Contract contract = contractDao.loadByKey(id);
        contract.setIsBlocked(blockLevel);
        logger.info("Contract block level has been changed. Contract id = " + contract.getId() + " block level = " + blockLevel);
        return new ContractDto(contract);
    }

    @Override
    public ContractDto updateContract(Integer contractId, Integer tariffId, List<Integer> optionIds, String number) {
        Contract contract = contractDao.loadByKey(contractId);
        // Check is contract exists
        if (contract == null) {
            return new ContractDto();
        }

        contract.getUsedOptions().size();
        Set<Option> oldOptions = contract.getUsedOptions();

        //Create new tariff
        Tariff tariff = new Tariff();
        tariff.setId(tariffId);
        contract.setTariff(tariff);

        //Set options for new tariff
        Set<Option> options = new HashSet<>();
        if (optionIds != null) {
            for (Integer id : optionIds) {
                Option opt = new Option();
                opt.setId(id);
                options.add(opt);
            }
        }
        contract.setUsedOptions(options);

        //Add number
        if (contract.getNumber() == null) {
            contract.setNumber(number);
        }

        //Save new contract
        contract = contractDao.add(contract);

        //Update new contract balance
        Set<Option> newOptions = contract.getUsedOptions();
        BigDecimal summ = newOptions.stream()
                .filter(e -> !optionIds.contains(e))
                .map(Option::getConnectCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        contract.setBalance(contract.getBalance().subtract(summ));
        logger.info("Contract has been updated. Id = " + contract.getId());

        return new ContractDto(contract).addDependencies(contract);
    }

    @Override
    @Transactional(readOnly = true)
    public ContractDto findByNumber(String number) {
        return new ContractDto(contractDao.findByNumber(number));
    }


    @Override
    @Transactional(readOnly = true)
    public ContractDto loadByKey(Integer key) {
        Contract contract = contractDao.loadByKey(key);
        // Return contract DTO object with dependencies
        return new ContractDto(contract).addDependencies(contract);
    }

}
