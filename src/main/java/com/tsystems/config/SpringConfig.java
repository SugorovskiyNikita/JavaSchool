package com.tsystems.config;

import com.tsystems.dao.CustomerDao;
import com.tsystems.dao.CustomerDaoImpl;
import com.tsystems.service.CustomerService;
import com.tsystems.service.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by nikita on 07.09.20.
 */
@Configuration
public class SpringConfig {

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/tmobile?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("76459");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public CustomerDao getCustomerDao() {
        return new CustomerDaoImpl(getJdbcTemplate());
    }

    @Bean
    public CustomerService getCustomerService() {
        return new CustomerServiceImpl();
    }

}
