package com.tsystems.db.interfaces;

import java.io.Serializable;
/**
 * Created by nikita on 05.09.20.
 */
public interface GenericDao<T, PK extends Serializable> {

    T create(T newInstance);

    T read(PK id);

    T update(T transientObject);

    void delete(PK id);
}
