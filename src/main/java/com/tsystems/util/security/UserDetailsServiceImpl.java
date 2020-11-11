package com.tsystems.util.security;

import com.tsystems.db.dao.interfaces.CustomerDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikita on 24.09.2020.
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService} interface.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerDao customerDao;

    public UserDetailsServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        return new SecurityUser(customerDao.findByEmail(email));
    }

}
