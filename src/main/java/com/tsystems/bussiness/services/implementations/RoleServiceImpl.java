package com.tsystems.bussiness.services.implementations;

import com.tsystems.db.dao.interfaces.RoleDao;
import com.tsystems.db.dto.RoleDto;
import com.tsystems.db.entities.Role;
import com.tsystems.bussiness.services.interfaces.RoleService;
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
