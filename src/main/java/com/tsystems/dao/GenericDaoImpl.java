package com.tsystems.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by nikita on 11.09.2020.
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;

    protected Class entityClass;

    public GenericDaoImpl() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public void add(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();

    }

    @Override
    public T getById(int id) {
        return (T) em.find(entityClass, id);
    }


    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }
}
