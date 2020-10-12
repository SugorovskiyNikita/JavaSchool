package com.tsystems.business.services.interfaces;

import com.tsystems.util.exceptions.WrongOptionConfigurationException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nikita on 15.09.2020.
 */
public interface GenericService<T, PK extends Serializable> {

    T add(T entityDto) throws WrongOptionConfigurationException;

    List<T> loadAll();

    T loadByKey(PK key);

    void remove(Integer PK);

}
