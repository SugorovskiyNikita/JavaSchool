package com.tsystems.business.services.interfaces;

import com.tsystems.db.dto.OptionDto;

import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */

public interface OptionService extends GenericService<OptionDto, Integer> {

    /**
     * Add new option
     * @param newOption new options with parameters
     * @param requiredFromId list of id required
     * @param forbiddenWithId list of id forbidden
     * @param forTariffsId list of id possible tariffs
     * @return option DTO object
     */
    OptionDto addNew(OptionDto newOption, String[] requiredFromId, String[] forbiddenWithId, List<Integer> forTariffsId);

    /**
     *
     * @param tariff id of tariff
     * @return list options DTO objects
     */
    List<OptionDto> getOptionsOfTariff(Integer tariff);
}
