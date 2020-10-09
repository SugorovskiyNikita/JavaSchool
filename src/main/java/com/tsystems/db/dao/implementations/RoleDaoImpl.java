package com.tsystems.db.dao.implementations;

import com.tsystems.db.dao.interfaces.RoleDao;
import com.tsystems.db.entities.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Created by nikita on 01.10.2020.
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Role getRoleById(Integer id) {
        return em.find(Role.class, id);
    }
}
