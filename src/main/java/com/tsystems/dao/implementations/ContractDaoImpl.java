package com.tsystems.dao.implementations;

import com.tsystems.dao.interfaces.ContractDao;
import com.tsystems.entities.Contract;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 15.09.2020.
 */
@Repository
public class ContractDaoImpl extends GenericDaoImpl<Contract, Integer> implements ContractDao {


    @Override
    public void add(Contract contract) {
        em.merge(contract);
    }

    @Override
    public List<Contract> loadAll() {
        return em.createQuery("SELECT NEW Contract (c.id, c.number, c.isBlocked, c.balance, c.customer,  c.tariff) FROM Contract c", Contract.class).getResultList();
    }

    @Override
    public Contract loadByKey(Integer key) {
        return em.find(Contract.class, key);
    }

    @Override
    public void remove(Contract contract) { em.remove(contract);}

    @Override
    public void update(Contract contract) {
        em.merge(contract);
    }


}
