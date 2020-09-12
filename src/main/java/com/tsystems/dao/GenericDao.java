package com.tsystems.dao;


/**
 * Created by nikita on 11.09.2020.
 */

public interface GenericDao<T> {

    void add(T entity);

    T getById(int id);

    void update(T entity);

    void delete(T entity);

}
