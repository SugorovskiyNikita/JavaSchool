package com.tsystems.security;

import com.tsystems.dao.interfaces.CustomerDao;
import com.tsystems.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikita on 24.09.2020.
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService} interface.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
            return new SecurityUser(customerDao.findByEmail(email));
    }

}
