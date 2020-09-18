package com.tsystems.dao.interfaces;

import com.tsystems.entities.Option;
import org.springframework.stereotype.Repository;


/**
 * Created by nikita on 16.09.2020.
 */
@Repository
public interface OptionDao extends GenericDao<Option, Integer> {
}
