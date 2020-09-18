package com.tsystems.dao.implementations;

import com.tsystems.dao.interfaces.TariffDao;
import com.tsystems.entities.Tariff;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 13.09.2020.
 */
@Repository
public class TariffDaoImpl extends GenericDaoImpl<Tariff, Integer> implements TariffDao {
    @Override
    public List<Tariff> loadAll() {
        return em.createQuery("SELECT  NEW Tariff(c.id, c.name, c.cost, c.description) FROM Tariff c", Tariff.class).getResultList();
    }

    @Override
    public void remove(Tariff tariff) {
        em.remove(tariff);
    }

    @Override
    public void add(Tariff tariff) {
        em.merge(tariff);
    }

    @Override
    public Tariff loadByKey(Integer key) {
        return em.find(Tariff.class, key);
    }

    @Override
    public void update(Tariff tariff) {
        em.merge(tariff);
    }
}
