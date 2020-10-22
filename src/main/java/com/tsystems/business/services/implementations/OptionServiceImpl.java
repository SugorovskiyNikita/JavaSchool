package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.OptionDao;
import com.tsystems.db.dao.interfaces.TariffDao;
import com.tsystems.db.dto.OptionDto;
import com.tsystems.db.entities.Option;
import com.tsystems.db.entities.Tariff;
import com.tsystems.business.services.interfaces.OptionService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nikita on 16.09.2020.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionDao optionDao;

    private final TariffDao tariffDao;

    private static final Logger logger = Logger.getLogger(ContractServiceImpl.class);

    @Override
    public OptionDto addNew(OptionDto newOption, String[] requiredFromId, String[] forbiddenWithId, List<Integer> forTariffsId) {

        Option option = newOption.convertToEntity();

        //Add the required options
        Set<Option> requireds = new HashSet<>();
        if (requiredFromId != null) {
            for (String id : requiredFromId) {
                Option opt = optionDao.loadByKey(Integer.parseInt(id));
                requireds.add(opt);
            }
        }
        //Add the forbidden options
        Set<Option> forbiddens = new HashSet<>();
        if (forbiddenWithId != null) {
            for (String id : forbiddenWithId) {
                Option opt = optionDao.loadByKey(Integer.parseInt(id));
                forbiddens.add(opt);
            }
        }
        option.setRequired(requireds);
        option.setForbidden(forbiddens);
        //Add tariffs for the option
        Set<Tariff> tariffs = new HashSet<>();
        if (forTariffsId != null) {
            for (Integer id : forTariffsId) {
                Tariff tar = tariffDao.loadByKey(id);
                tariffs.add(tar);
            }
        }
        option.setPossibleTariffsOfOption(tariffs);

        try {
            optionDao.add(option);

            logger.info("New option has been created. Name = " + option.getName());
        } catch (Exception e) {
            logger.warn("Option error adding to the DB" + e);
        }

        return new OptionDto(option).addDependencies(option);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDto> loadAll() {
        return optionDao
                .loadAll()
                .stream().map(e -> new OptionDto(e).addDependencies(e))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Integer key) {
        optionDao.remove(key);
    }


    @Override
    @Transactional(readOnly = true)
    public OptionDto loadByKey(Integer key) {
        Option option = optionDao.loadByKey(key);
        return new OptionDto(option).addDependencies(option);
    }

    @Override
    public List<OptionDto> getOptionsOfTariff(Integer tariff) {
        List<Option> options = optionDao.getOptionsForTariff(tariff);
        return options.stream().map(e -> new OptionDto(e).addDependencies(e))
                .collect(Collectors.toList());
    }
}
