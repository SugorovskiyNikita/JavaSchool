package com.tsystems.db.dao.implementations;

import com.tsystems.config.MessageConfig;
import com.tsystems.db.dao.interfaces.TariffDao;
import com.tsystems.db.entities.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 13.09.2020.
 */
@Repository
public class TariffDaoImpl extends GenericDaoImpl<Tariff, Integer> implements TariffDao {

    final MessageConfig messageConfig;

    public TariffDaoImpl(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    @Override
    public List<Tariff> loadAll() {
        return em.createQuery("SELECT t FROM Tariff t", Tariff.class)
                .getResultList();
    }

    @Override
    public void remove(Integer id) {

        em.remove(em.getReference(Tariff.class, id));
        messageConfig.sendMessage("Update tariffs");
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
        messageConfig.sendMessage("Update tariffs");
    }

}
