package com.tsystems.db.mapper;

import com.tsystems.db.entities.Tariff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nikita on 13.09.2020.
 */
public class TariffMapper implements RowMapper<Tariff> {
    @Override
    public Tariff mapRow(ResultSet resultSet, int i) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(resultSet.getInt("id"));
        tariff.setName(resultSet.getString("name"));
        tariff.setCost(resultSet.getBigDecimal("cost"));
        tariff.setDescription(resultSet.getString("description"));
        return tariff;
    }
}
