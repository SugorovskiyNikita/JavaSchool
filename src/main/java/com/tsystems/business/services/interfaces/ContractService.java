package com.tsystems.business.services.interfaces;

import com.tsystems.db.dto.ContractDto;
import netscape.javascript.JSException;

import java.util.List;

/**
 * Created by nikita on 15.09.2020.
 */
public interface ContractService extends GenericService<ContractDto, Integer> {
    /**
     * Set block level to contract
     * 0 - unblocked
     * 1 - blocked by customer
     * 2 - blocked by admin
     *
     * @param id         id of contract
     * @param blockLevel new block level
     */
    ContractDto setBlock(Integer id, Integer blockLevel);

    /**
     * Update contract by id with new tariff and options
     *
     * @param contractId id of contract
     * @param tariffId   id of new tariff
     * @param optionIds  ids of new options
     * @return updated contract
     */
    ContractDto updateContract(Integer contractId, Integer tariffId, List<Integer> optionIds, String number) throws JSException;

    /**
     * Finding contract by number
     * @param number number to search
     * @return found contract DTO object
     */
    ContractDto findByNumber(String number);

    /**
     * Add new contract for customer
     * @param customerId id of customer
     * @return new contract DTO object
     */
    ContractDto addNew(Integer customerId);

}
