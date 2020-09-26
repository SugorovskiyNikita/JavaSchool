package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.OptionDao;
import com.tsystems.dto.OptionDto;
import com.tsystems.entities.Option;
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

    private final OptionDao optionDao;

    @Autowired
    public OptionServiceImpl(OptionDao optionDao) {
        this.optionDao = optionDao;
    }

    @Override
    public OptionDto add(OptionDto entity) throws WrongOptionConfigurationException {

        Option option = entity.convertToEntity();

        Set<Option> requireds = option.getRequired().stream()
                .map(e -> optionDao.loadByKey(e.getId())).collect(Collectors.toSet());
        Set<Option> forbiddens = option.getForbidden().stream()
                .map(e -> optionDao.loadByKey(e.getId())).collect(Collectors.toSet());

        option.setRequired(new HashSet<>());
        option.setForbidden(new HashSet<>());

        for (Option req : requireds) {
            option.addRequiredFromOptions(req);
            option.addRequiredFromOptions(req.getForbidden());
            option.addForbiddenWithOptions(req.getForbidden());

            if (forbiddens.contains(req))
                throw new WrongOptionConfigurationException(1);
            if (req.getForbidden().stream().anyMatch(requireds::contains))
                throw new WrongOptionConfigurationException(2);
            if (req.getRequired().stream().anyMatch(forbiddens::contains))
                throw new WrongOptionConfigurationException(3);
        }

        for (Option forb : forbiddens) {
            option.addForbiddenWithOptions(forb);
            option.addForbiddenWithOptions(forb.getRequiredMe());

            // This case exists already in required for block, but let it be
            if (forb.getRequiredMe().stream().anyMatch(requireds::contains))
                throw new WrongOptionConfigurationException(3);
        }

        Option saved = optionDao.add(option);
        return new OptionDto(saved).addDependencies(saved);
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
