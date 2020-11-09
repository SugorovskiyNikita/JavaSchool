package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.OptionDao;
import com.tsystems.db.dao.interfaces.TariffDao;
import com.tsystems.db.dto.TariffDto;
import com.tsystems.db.entities.Option;
import com.tsystems.db.entities.Tariff;
import com.tsystems.business.services.interfaces.TariffService;
import com.tsystems.util.variable.Variable;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nikita on 13.09.2020.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TariffServiceImpl extends Variable implements TariffService {


    final MessageService messageService;

    private final TariffDao tariffDao;

    private final OptionDao optionDao;

    private static final Logger logger = Logger.getLogger(TariffServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<TariffDto> loadAll() {
        logger.info("Loading all tariffs from the database.");
        return tariffDao
                .loadAll()
                .stream()
                .map(e -> new TariffDto(e).addDependencies(e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TariffDto loadByKey(Integer id) {
        Tariff tariff = tariffDao.loadByKey(id);
        return new TariffDto(tariff).addDependencies(tariff);
    }

    @Override
    public void remove(Integer id) {
        tariffDao.remove(id);
        messageService.sendMessage(UPDATE_TARIFF);
        logger.info("Tariff was deleted. Id = " + id);
    }

    @Override
    public TariffDto update(Integer id, List<Integer> newOptions, String name, BigDecimal cost, String description) {
        Tariff tariff = tariffDao.loadByKey(id);
        tariff.setName(name);
        tariff.setCost(cost);
        tariff.setDescription(description);
        Set<Option> options = new HashSet<>();
        if (newOptions != null) {
            for (Integer ids : newOptions) {
                Option opt = optionDao.loadByKey(ids);
                options.add(opt);
            }
        }
        tariff.setPossibleOptions(options);
        logger.info("Tariff has been updated. Id = " + tariff.getId());
        messageService.sendMessage(UPDATE_TARIFF);

        return new TariffDto(tariffDao.add(tariff)).addDependencies(tariff);
    }

    @Override
    public TariffDto addNew(TariffDto tariffDto, List<Integer> newOptions) {
        Tariff tariff = tariffDto.convertToEntity();
        Set<Option> options = new HashSet<>();
        if (newOptions != null) {
            for (Integer id : newOptions) {
                Option opt = optionDao.loadByKey(id);
                options.add(opt);
            }
        }
        tariff.setPossibleOptions(options);
        try {
            tariffDao.add(tariff);
            messageService.sendMessage(UPDATE_TARIFF);
            logger.info("New contract is created. Number = " + tariff.getName());
        } catch (Exception e) {
            logger.warn("Tariff error adding to the DB" + e);
        }

        return new TariffDto(tariff).addDependencies(tariff);
    }
}
