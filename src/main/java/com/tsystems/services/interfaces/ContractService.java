package com.tsystems.services.interfaces;

import com.tsystems.dto.ContractDto;
import netscape.javascript.JSException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nikita on 15.09.2020.
 */
@Service
public interface ContractService extends GenericService<ContractDto, Integer> {


    ContractDto setBlock(Integer id, Integer blockLevel);

    ContractDto updateContract(Integer contractId, Integer tariffId, List<Integer> optionIds, String number) throws JSException;

    ContractDto findByNumber(String number);


}
