package com.tsystems.dao.implementations;

import com.tsystems.dao.interfaces.TariffDao;
import com.tsystems.entities.Tariff;
import org.apache.tools.ant.taskdefs.Tar;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 13.09.2020.
 */
@Repository
public class TariffDaoImpl extends GenericDaoImpl<Tariff, Integer> implements TariffDao {
    @Override
    public List<Tariff> getAll() {
        return em.createQuery("SELECT NEW Tariff(c.id, c.name, c.cost, c.description) FROM Tariff c", Tariff.class).getResultList();
    }
}
