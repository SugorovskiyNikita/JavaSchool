package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.TariffDao;
import com.tsystems.dao.implementations.TariffDaoImpl;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.TariffService;
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
    public void add(Tariff tariff) {
        tariffDao.add(tariff);
    }
}
