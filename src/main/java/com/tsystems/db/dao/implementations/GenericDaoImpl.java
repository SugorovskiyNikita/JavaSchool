package com.tsystems.db.dao.implementations;

import com.tsystems.db.dao.interfaces.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

/**
 * Created by nikita on 11.09.2020.
 */
public abstract class GenericDaoImpl<T, Integer> implements GenericDao<T, Integer> {

    @PersistenceContext
    protected EntityManager em;

    protected Class entityClass;

    public GenericDaoImpl() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public T add(T entity) {
        return em.merge(entity);
    }

    @Override
    public T loadByKey(Integer key) {
        return (T) em.find(entityClass, key);
    }

    @Override
    public void update(T entity) {
        em.merge(entity);
    }

    @Override
    public void remove(java.lang.Integer entity) {
        em.remove(entity);
    }

}
