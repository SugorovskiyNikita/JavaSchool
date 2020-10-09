package com.tsystems.db.dao.interfaces;

import com.tsystems.db.entities.Tariff;
import org.springframework.stereotype.Repository;

/**
 * Created by nikita on 13.09.2020.
 */
@Repository
public interface TariffDao extends GenericDao<Tariff, Integer> {
}
