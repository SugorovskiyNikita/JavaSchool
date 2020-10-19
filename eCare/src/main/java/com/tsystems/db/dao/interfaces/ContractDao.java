package com.tsystems.db.dao.interfaces;

import com.tsystems.db.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Created by nikita on 15.09.2020.
 *
 * Repository for contract
 */
public interface ContractDao extends JpaRepository<Contract, Integer> {
    /**
     * Search for contract by number
     * @param number search number
     * @return contract entity
     */
    Contract findByNumber(String number);
}
