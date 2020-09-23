package com.tsystems.services.implementations;

import com.tsystems.dao.implementations.ContractDaoImpl;
import com.tsystems.dao.interfaces.ContractDao;
import com.tsystems.dao.interfaces.CustomerDao;
import com.tsystems.entities.Contract;
import com.tsystems.services.interfaces.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by nikita on 15.09.2020.
 */
@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractDao contractDao;

    @Autowired
    public ContractServiceImpl(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    @Override
    public void add(Contract contract) {
        contractDao.add(contract);
    }

    @Override
    public List<Contract> loadAll() {
        return contractDao.loadAll();
    }

    @Override
    public void remove(Contract contract) {
        contractDao.remove(contract);
    }

    @Override
    public void update(Contract contract) {
        contractDao.update(contract);
    }

    @Override
    public Contract loadByKey(Integer key) {
        return contractDao.loadByKey(key);
    }
}
