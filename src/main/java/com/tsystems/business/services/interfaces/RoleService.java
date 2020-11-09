package com.tsystems.business.services.interfaces;

import com.tsystems.db.dto.RoleDto;

/**
 * Created by nikita on 08.10.2020.
 */
public interface RoleService {

    /**
     *
     * @param id id of role
     * @return role DTO object
     */
    RoleDto getRoleById(Integer id);

    /**
     *
     * @param roleName name of role
     * @return role DTO object
     */
    RoleDto findByName(String roleName);

}
