package com.tsystems.dao.implementations;

import com.tsystems.dao.interfaces.OptionDao;
import com.tsystems.entities.Option;
import com.tsystems.entities.Tariff;

import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */
public class OptionDaoImpl extends GenericDaoImpl<Option, Integer> implements OptionDao {

    @Override
    public List<Option> getAll() {
        return em.createQuery("SELECT c FROM Option c", Option.class).getResultList();
    }

    @Override
    public void add(Option option) {
        em.merge(option);
    }
}
