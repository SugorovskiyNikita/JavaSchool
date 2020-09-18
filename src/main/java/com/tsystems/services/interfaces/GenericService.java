package com.tsystems.services.interfaces;

import com.tsystems.entities.Contract;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nikita on 15.09.2020.
 */
public interface GenericService<T, PK extends Serializable> {

    void add(T entity);

    List<T> loadAll();

    T loadByKey(PK key);

    void remove(T entity);

    void update(T entity);

}
