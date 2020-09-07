package com.tsystems.db.implimentations;

import com.tsystems.db.interfaces.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by nikita on 05.09.20.
 */
public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
    protected EntityManager em = Persistence.createEntityManagerFactory("mobile").createEntityManager();

    private Class type;

    public GenericDaoImpl() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T create(T newInstance) {
        em.getTransaction().begin();
        em.persist(newInstance);
        em.getTransaction().commit();
        return newInstance;
    }
    @Override
    public T read(PK id) {
        return (T) em.find(type, id);
    }

    @Override
    public T update(T transientObject) {
        em.getTransaction().begin();
        em.merge(transientObject);
        em.getTransaction().commit();
        return transientObject;
    }

    @Override
    public void delete(PK id) {
        em.getTransaction().begin();
        em.remove(em.getReference(type, id));
        em.getTransaction().commit();
    }

}
