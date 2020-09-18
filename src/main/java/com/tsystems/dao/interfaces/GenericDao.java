package com.tsystems.dao.interfaces;


import java.util.List;

/**
 * Created by nikita on 11.09.2020.
 */
public interface GenericDao<T, PK> {

    void add(T entity);

    List<T> loadAll();

    T loadByKey(PK key);

    void remove(T entity);

    void update(T Entity);


}
