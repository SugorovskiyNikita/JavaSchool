package com.tsystems.dao;

import com.tsystems.entities.Customer;
import com.tsystems.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikita on 07.09.2020.
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, lastname, date_of_birth, passport_number, passport_data, address, email, password, is_blocked) VALUE (?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, customer.getName(), customer.getLastname(), customer.getDateOfBirth(), customer.getPassportNumber(), customer.getPassportData(), customer.getAddress(), customer.getEmail(), customer.getPassword(), customer.getIsBlocked());
    }

    @Override
    public Customer getById(int id) {
        String sql = "SELECT * FROM customers WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new CustomerMapper(), id);
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, new CustomerMapper());
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customers SET name=?, lastname=?, date_of_birth=?, passport_number=?, passport_data=?, address=?, email=?, password=?, is_blocked=? WHERE id=?";
        jdbcTemplate.update(sql, customer.getName(), customer.getLastname(), customer.getDateOfBirth(), customer.getPassportNumber(), customer.getPassportData(), customer.getAddress(), customer.getEmail(), customer.getPassword(), customer.getIsBlocked(), customer.getId());

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM customers WHERE id=?";
        jdbcTemplate.update(sql, id);

    }
}
