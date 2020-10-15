package com.tsystems.db.dao.implementations;

import com.tsystems.db.dao.interfaces.ContractDao;
import com.tsystems.db.entities.Contract;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 15.09.2020.
 */
@Repository
public class ContractDaoImpl extends GenericDaoImpl<Contract, Integer> implements ContractDao {


    @Override
    public Contract add(Contract contract) {
        return em.merge(contract);
    }

    @Override
    public List<Contract> loadAll() {
        return em.createQuery("SELECT t FROM Contract t", Contract.class)
                .getResultList();
    }

    @Override
    public Contract loadByKey(Integer key) {
        return em.find(Contract.class, key);
    }

    @Override
    public void remove(Integer id) {
        em.remove(id);
    }

    @Override
    public void update(Contract contract) {
        em.merge(contract);
    }

    @Override
    public Contract findByNumber(String number) {
        return em.find(Contract.class, number);
    }
}
