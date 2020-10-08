package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.RoleDao;
import com.tsystems.dto.OptionDto;
import com.tsystems.dto.RoleDto;
import com.tsystems.entities.Option;
import com.tsystems.entities.Role;
import com.tsystems.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nikita on 08.10.2020.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public RoleDto getRoleById(Integer id) {
        Role role = roleDao.getRoleById(id);
        return new RoleDto(role);
    }
}
