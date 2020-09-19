package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.TariffDao;
import com.tsystems.dao.implementations.TariffDaoImpl;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public void add(Tariff tariff) {
        tariffDao.add(tariff);
    }

    @Override
    public List<Tariff> loadAll() {
        return tariffDao.loadAll();
    }

    @Override
    public Tariff loadByKey(Integer key) {
        return tariffDao.loadByKey(key);
    }

    @Override
    public void remove(Tariff tariff) {
        tariffDao.remove(tariff);
    }

    @Override
    public void update(Tariff tariff) {
        tariffDao.update(tariff);
    }
}
