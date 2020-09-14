package com.tsystems.dao.interfaces;


/**
 * Created by nikita on 11.09.2020.
 */
public interface GenericDao<T, Integer> {

    void add(T entity);

    T getById(int id);

    void update(T entity);

    void delete(T entity);
}
