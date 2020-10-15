package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.OptionDao;
import com.tsystems.db.dao.interfaces.TariffDao;
import com.tsystems.db.dto.TariffDto;
import com.tsystems.db.entities.Option;
import com.tsystems.db.entities.Tariff;
import com.tsystems.business.services.interfaces.TariffService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TariffServiceImpl implements TariffService {

    @Autowired
    private TariffDao tariffDao;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private static final Logger logger = Logger.getLogger(ContractServiceImpl.class);

    @Override
    public TariffDto add(TariffDto tariffDto) {
        Tariff tariff = tariffDto.convertToEntity();
        try {
            tariffDao.add(tariff);
            logger.info("New tariff created. Name = " + tariff.getName());
        } catch (Exception e) {
            logger.warn("Tariff" + tariff.getName() + "error adding to the DB" + e);
        }
        return new TariffDto(tariff);

    }

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
    public TariffDto loadByKey(Integer key) {
        Tariff tariff = tariffDao.loadByKey(key);
        return new TariffDto(tariff).addDependencies(tariff);
    }

    @Override
    public void remove(Integer key) {
        tariffDao.remove(key);
        logger.info("Tariff was deleted. Id = " + key);
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

        return new TariffDto(tariffDao.add(tariff)).addDependencies(tariff);
    }

    @Override
    public TariffDto addNew(TariffDto tariff, List<Integer> newOptions) {
        Tariff tar = tariff.convertToEntity();
        Set<Option> options = new HashSet<>();
        if (newOptions != null) {
            for (Integer id : newOptions) {
                Option opt = optionDao.loadByKey(id);
                options.add(opt);
            }
        }
        tar.setPossibleOptions(options);
        try {
            tariffDao.add(tar);
            logger.info("New contract is created. Number = " + tar.getName());
        } catch (Exception e) {
            logger.warn("Tariff error adding to the DB" + e);
        }

        return new TariffDto(tar).addDependencies(tar);
    }
}
