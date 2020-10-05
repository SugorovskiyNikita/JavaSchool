package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.OptionDao;
import com.tsystems.dao.interfaces.TariffDao;
import com.tsystems.dto.OptionDto;
import com.tsystems.entities.Option;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.OptionService;
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
    public OptionDto addNew(OptionDto newOption, List<Integer> requiredFromId, List<Integer> forbiddenWithId, List<Integer> forTariffsId) throws WrongOptionConfigurationException {

        Option option = newOption.convertToEntity();

        Set<Option> requireds = new HashSet<>();
        if (requiredFromId != null) {
            for (Integer id : requiredFromId) {
                Option opt = optionDao.loadByKey(id);
                requireds.add(opt);
            }
        }

        Set<Option> forbiddens = new HashSet<>();
        if (forbiddenWithId != null) {
            for (Integer id : forbiddenWithId) {
                Option opt = optionDao.loadByKey(id);
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
        return new OptionDto(saved);
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
}
