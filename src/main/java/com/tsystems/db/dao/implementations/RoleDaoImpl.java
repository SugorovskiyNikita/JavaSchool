package com.tsystems.db.dao.implementations;

import com.tsystems.db.dao.interfaces.RoleDao;
import com.tsystems.db.entities.Customer;
import com.tsystems.db.entities.Customer_;
import com.tsystems.db.entities.Role;
import com.tsystems.db.entities.Role_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


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

    @Override
    public Role findByName(String roleName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get(Role_.roleName), roleName));
        TypedQuery<Role> selectByName = em.createQuery(criteriaQuery);

        Role role = selectByName.getSingleResult();
        return role;
    }
}
