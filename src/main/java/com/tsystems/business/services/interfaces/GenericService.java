package com.tsystems.business.services.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nikita on 15.09.2020.
 */
public interface GenericService<T, Integer extends Serializable> {

    /**
     * Load all entries
     *
     * @return all entries
     */
    List<T> loadAll();

    /**
     * Find entity by id
     *
     * @param id id of entity
     * @return loaded entity
     */
    T loadByKey(Integer id);

    /**
     * Remove entity by id
     *
     * @param id id of entity
     */
    void remove(Integer id);

}
