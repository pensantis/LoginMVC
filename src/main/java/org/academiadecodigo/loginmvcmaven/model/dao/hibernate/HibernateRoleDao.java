package org.academiadecodigo.loginmvcmaven.model.dao.hibernate;

import org.academiadecodigo.loginmvcmaven.Persistence.TransactionException;
import org.academiadecodigo.loginmvcmaven.Persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.loginmvcmaven.model.Role;
import org.academiadecodigo.loginmvcmaven.model.dao.RoleDao;
import org.hibernate.HibernateException;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateRoleDao implements RoleDao {
    @Override
    public void save(Role role) {
        try {
            HibernateSessionManager.getSession().save(role);
        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public void delete(Role role) {

    }

    @Override
    public Role findByName(String rolename) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
