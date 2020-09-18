package com.tsystems.dao.implementations;

import com.tsystems.dao.interfaces.OptionDao;
import com.tsystems.entities.Option;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */
@Repository
public class OptionDaoImpl extends GenericDaoImpl<Option, Integer> implements OptionDao {

    @Override
    public void add(Option option) {
        em.merge(option);
    }

    @Override
    public List<Option> loadAll() {
        return em.createQuery("SELECT NEW Option (c.id, c.name, c.cost, c.connectCost, c.description) FROM Option c", Option.class)
                .getResultList();
    }

    @Override
    public Option loadByKey(Integer key) {
        return em.find(Option.class, key);
    }

    @Override
    public void remove(Option option) {
        em.remove(option);
    }

    @Override
    public void update(Option option) {
        em.merge(option);
    }
}
