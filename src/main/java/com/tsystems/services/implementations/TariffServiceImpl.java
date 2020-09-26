package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.TariffDao;
import com.tsystems.dto.TariffDto;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nikita on 13.09.2020.
 */
@Service
@Transactional
public class TariffServiceImpl implements TariffService {

    private final TariffDao tariffDao;

    @Autowired
    public TariffServiceImpl(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
    }

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


}
