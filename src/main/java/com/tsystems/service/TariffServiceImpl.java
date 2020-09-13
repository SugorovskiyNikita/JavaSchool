package com.tsystems.service;

import com.tsystems.dao.TariffDao;
import com.tsystems.dao.TariffDaoImpl;
import com.tsystems.entities.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by nikita on 13.09.2020.
 */
@Service
@Transactional
public class TariffServiceImpl implements TariffService {

    @Autowired
    private TariffDao tariffDao = new TariffDaoImpl();

    @Override
    public void addTariff(Tariff tariff) {
        tariffDao.add(tariff);
    }
}
