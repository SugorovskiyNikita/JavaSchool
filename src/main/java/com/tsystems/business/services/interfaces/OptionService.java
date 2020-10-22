package com.tsystems.business.services.interfaces;

import com.tsystems.db.dto.OptionDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */
@Service
public interface OptionService extends GenericService<OptionDto, Integer> {
    OptionDto addNew(OptionDto newOption, String[] requiredFromId, String[] forbiddenWithId, List<Integer> forTariffsId);

    List<OptionDto> getOptionsOfTariffs(Integer tariffs);
}
