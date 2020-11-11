package com.tsystems.business.services.interfaces;

import com.tsystems.db.dto.TariffDto;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by nikita on 13.09.2020.
 */

public interface TariffService extends GenericService<TariffDto, Integer> {

    /**
     *
     * @param tariffDto id if tariff
     * @param newOptions list of id options
     * @param name new name for tariff
     * @param cost new cost for tariff
     * @param description new description for tariff
     * @return update tariff DTO object
     */
    TariffDto update(Integer tariffDto, List<Integer> newOptions, String name, BigDecimal cost, String description) throws NamingException;


    /**
     *
     * @param tariff tariff with all parameters
     * @param newOptions list of id options
     * @return new tariff DTO object
     */
    TariffDto addNew(TariffDto tariff, List<Integer> newOptions);

}
