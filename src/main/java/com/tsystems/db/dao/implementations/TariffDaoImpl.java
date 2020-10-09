package com.tsystems.db.dao.implementations;

import com.tsystems.db.dao.interfaces.TariffDao;
import com.tsystems.db.entities.Tariff;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 13.09.2020.
 */
@Repository
public class TariffDaoImpl extends GenericDaoImpl<Tariff, Integer> implements TariffDao {
    @Override
    public List<Tariff> loadAll() {
        return em.createQuery("SELECT NEW Tariff(c.id, c.name, c.cost, c.description) FROM Tariff c", Tariff.class)
                .getResultList();
    }

    @Override
    public void remove(Integer tariff) {
        em.remove(tariff);
    }

    @Override
    public Tariff add(Tariff tariff) {
        return em.merge(tariff);
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
