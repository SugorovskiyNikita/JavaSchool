package com.tsystems.db.mapper;

import com.tsystems.db.entities.Option;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nikita on 19.09.2020.
 */
public class OptionMapper implements RowMapper<Option> {

    @Override
    public Option mapRow(ResultSet resultSet, int i) throws SQLException {
        Option option = new Option();
        option.setId(resultSet.getInt("id"));
        option.setName(resultSet.getString("name"));
        option.setCost(resultSet.getBigDecimal("cost"));
        option.setConnectCost(resultSet.getBigDecimal("connectCost"));
        option.setDescription(resultSet.getString("description"));
        return option;
    }
}
