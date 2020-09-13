package com.tsystems.services.interfaces;

import com.tsystems.entities.Tariff;
import org.springframework.stereotype.Service;

/**
 * Created by nikita on 13.09.2020.
 */
@Service
public interface TariffService {
    void addTariff(Tariff tariff);
}
