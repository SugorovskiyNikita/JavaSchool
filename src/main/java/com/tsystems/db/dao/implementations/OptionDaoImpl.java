package com.tsystems.db.dao.implementations;

import com.tsystems.db.dao.interfaces.OptionDao;
import com.tsystems.db.entities.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */
@Repository
public class OptionDaoImpl extends GenericDaoImpl<Option, Integer> implements OptionDao {

    @Override
    public Option add(Option option) {
        return em.merge(option);
    }

    @Override
    public List<Option> loadAll() {
        return em.createQuery("SELECT t FROM Option t", Option.class)
                .getResultList();
    }

    @Override
    public Option loadByKey(Integer key) {
        return em.find(Option.class, key);
    }

    @Override
    public void remove(Integer option) {
        em.remove(option);
    }

    @Override
    public void update(Option option) {
        em.merge(option);
    }

    @Override
    public List<Option> getOptionsForTariff(Integer tariffs) {
        return em.createQuery("SELECT distinct o FROM Option o JOIN o.possibleTariffsOfOption t WHERE t.id IN :tariffs"
                , Option.class)
                .setParameter("tariffs", tariffs)
                .getResultList();
    }
}
