package com.tsystems.dao.interfaces;

import com.tsystems.entities.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by nikita on 01.10.2020.
 */
@Repository
public interface RoleDao {
    Role getRoleById(Integer id);
}
