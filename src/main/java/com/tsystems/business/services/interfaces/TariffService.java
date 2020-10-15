package com.tsystems.business.services.interfaces;

import com.tsystems.db.dto.TariffDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by nikita on 13.09.2020.
 */
@Service
public interface TariffService extends GenericService<TariffDto, Integer> {

    TariffDto update(Integer tariffDto, List<Integer> newOptions, String name, BigDecimal cost, String description);

    TariffDto addNew(TariffDto tariff, List<Integer> newOptions);

}
