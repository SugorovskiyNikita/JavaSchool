package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.OptionDao;
import com.tsystems.db.dao.interfaces.TariffDao;
import com.tsystems.db.dto.OptionDto;
import com.tsystems.db.entities.Option;
import com.tsystems.db.entities.Tariff;
import com.tsystems.business.services.interfaces.OptionService;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;
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
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private TariffDao tariffDao;


    @Override
    public OptionDto addNew(OptionDto newOption, String[] requiredFromId, String[] forbiddenWithId, List<Integer> forTariffsId) throws WrongOptionConfigurationException {

        Option option = newOption.convertToEntity();

        Set<Option> requireds = new HashSet<>();
        if (requiredFromId != null) {
            for (String id : requiredFromId) {
                Option opt = optionDao.loadByKey(Integer.parseInt(id));
                requireds.add(opt);
            }
        }

        Set<Option> forbiddens = new HashSet<>();
        if (forbiddenWithId != null) {
            for (String id : forbiddenWithId) {
                Option opt = optionDao.loadByKey(Integer.parseInt(id));
                forbiddens.add(opt);
            }
        }

        option.setRequired(requireds);
        option.setForbidden(forbiddens);

        Set<Tariff> tariffs = new HashSet<>();
        if (forTariffsId != null) {
            for (Integer id : forTariffsId) {
                Tariff tar = tariffDao.loadByKey(id);
                tariffs.add(tar);
            }
        }
        option.setPossibleTariffsOfOption(tariffs);

        Option saved = optionDao.add(option);
        return new OptionDto(saved).addDependencies(saved);
    }

    @Override
    public OptionDto add(OptionDto entityDto) throws WrongOptionConfigurationException {
        return null;
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
    public List<OptionDto> getOptionsOfTariffs(Integer tariffs) {
        List<Option> options = optionDao.getOptionsForTariff(tariffs);
        return options.stream().map(e -> new OptionDto(e).addDependencies(e))
                .collect(Collectors.toList());
    }
}
