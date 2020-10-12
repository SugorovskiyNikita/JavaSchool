package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.OptionDao;
import com.tsystems.db.dao.interfaces.TariffDao;
import com.tsystems.db.dto.TariffDto;
import com.tsystems.db.entities.Option;
import com.tsystems.db.entities.Tariff;
import com.tsystems.business.services.interfaces.TariffService;
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

    @Override
    public TariffDto add(TariffDto tariffDto) {
        Tariff tariff = tariffDto.convertToEntity();
        return new TariffDto(tariffDao.add(tariff));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TariffDto> loadAll() {
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
    }

    @Override
    public TariffDto update(TariffDto tariffDto, List<Integer> newOptions, String name, Integer cost, String description) {
        Tariff tariff = tariffDto.convertToEntity();
        tariff.setName(name);
        tariff.setCost(new BigDecimal(cost));
        tariff.setDescription(description);
        Set<Option> options = new HashSet<>();
        if (newOptions != null) {
            for (Integer id : newOptions) {
                Option opt = optionDao.loadByKey(id);
                opt.setId(id);
                options.add(opt);
            }
        }
        tariff.setPossibleOptions(options);
        return new TariffDto(tariff).addDependencies(tariff);
    }

    @Override
    public TariffDto addNew(TariffDto tariff, List<Integer> newOptions) {
        Tariff tar = tariff.convertToEntity();
        Set<Option> options = new HashSet<>();
        if (newOptions != null) {
            for (Integer id : newOptions) {
                Option opt = optionDao.loadByKey(id);
                opt.setId(id);
                options.add(opt);
            }
        }
        tar.setPossibleOptions(options);
        return new TariffDto(tariffDao.add(tar)).addDependencies(tar);
    }
}
