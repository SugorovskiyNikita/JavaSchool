package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.RoleDao;
import com.tsystems.db.dto.RoleDto;
import com.tsystems.db.entities.Role;
import com.tsystems.business.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nikita on 08.10.2020.
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Override
    public RoleDto getRoleById(Integer id) {
        Role role = roleDao.getRoleById(id);
        return new RoleDto(role);
    }

    @Override
    public RoleDto findByName(String roleName) {
        Role role = roleDao.findByName(roleName);
        return new RoleDto(role);
    }
}
