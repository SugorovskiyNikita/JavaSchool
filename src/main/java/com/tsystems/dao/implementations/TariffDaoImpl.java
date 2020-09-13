package com.tsystems.dao.implementations;

import com.tsystems.dao.interfaces.TariffDao;
import com.tsystems.entities.Tariff;
import org.springframework.stereotype.Repository;

/**
 * Created by nikita on 13.09.2020.
 */
@Repository
public class TariffDaoImpl extends GenericDaoImpl<Tariff> implements TariffDao {
}
