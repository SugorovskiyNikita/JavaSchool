package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.ContractDao;
import com.tsystems.dao.interfaces.TariffDao;
import com.tsystems.dto.ContractDto;
import com.tsystems.dto.TariffDto;
import com.tsystems.entities.Contract;
import com.tsystems.entities.Option;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.ContractService;
import com.tsystems.services.interfaces.TariffService;
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
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private TariffDao tariffDao;

    @Override
    public ContractDto add(ContractDto contractDto) {
        // Create new contract. Default balance on new contract == 100 and non blocked
        Contract contract = contractDto.convertToEntity();
        contract.setBalance(new BigDecimal("100.00"));
        contract.setIsBlocked(0);
        return new ContractDto(contractDao.add(contract));

    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractDto> loadAll() {
        return contractDao
                .loadAll()
                .stream()
                .map(e -> new ContractDto(e).addDependencies(e))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Integer key) {
        contractDao.remove(key);
    }

    @Override
    public ContractDto setBlock(Integer id, Integer blockLevel) {
        return null;
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
        contract.setNumber(number);

        //Save new contract
        contract = contractDao.add(contract);

        //Update new contract balance
        Set<Option> newOptions = contract.getUsedOptions();
        BigDecimal summ = newOptions.stream()
                .filter(e -> !optionIds.contains(e))
                .map(Option::getConnectCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        contract.setBalance(contract.getBalance().subtract(summ));

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
