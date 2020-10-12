package com.tsystems.db.dao.interfaces;

import com.tsystems.db.entities.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by nikita on 01.10.2020.
 */
@Repository
public interface RoleDao {

    Role getRoleById(Integer id);

    Role findByName(String roleName);
}
