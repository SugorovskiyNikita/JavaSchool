package com.tsystems.db.dao.interfaces;

import com.tsystems.db.entities.Contract;
import org.springframework.stereotype.Repository;

/**
 * Created by nikita on 15.09.2020.
 */
@Repository
public interface ContractDao extends GenericDao<Contract, Integer> {

    Contract findByNumber(String number);
}
