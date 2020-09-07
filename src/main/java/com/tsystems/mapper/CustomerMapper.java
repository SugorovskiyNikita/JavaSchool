package com.tsystems.mapper;

import com.tsystems.entities.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nikita on 07.09.2020.
 */
public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setAddress(resultSet.getString("address"));
        customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
        customer.setPassportNumber(resultSet.getString("passport_number"));
        customer.setEmail(resultSet.getString("email"));
        customer.setIsBlocked(resultSet.getInt("is_blocked"));
        customer.setName(resultSet.getString("name"));
        customer.setLastname(resultSet.getString("lastname"));
        customer.setPassportData(resultSet.getString("passport_date"));
        customer.setPassword(resultSet.getString("password"));
        return customer;
    }
}
