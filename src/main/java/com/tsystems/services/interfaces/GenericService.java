package com.tsystems.services.interfaces;

import java.util.List;

/**
 * Created by nikita on 15.09.2020.
 */
public interface GenericService<T> {
    void add(T entity);

    List<T> loadAll();

    T loadByKey(Integer key);

}
