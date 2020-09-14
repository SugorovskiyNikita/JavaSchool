package com.tsystems.dao.interfaces;

import com.tsystems.entities.Tariff;
import org.springframework.stereotype.Repository;

/**
 * Created by nikita on 13.09.2020.
 */
@Repository
public interface TariffDao extends GenericDao<Tariff, Integer> {
}
