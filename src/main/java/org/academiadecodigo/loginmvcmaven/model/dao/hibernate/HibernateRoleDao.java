package org.academiadecodigo.loginmvcmaven.model.dao.hibernate;

import org.academiadecodigo.loginmvcmaven.Persistence.TransactionException;
import org.academiadecodigo.loginmvcmaven.Persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.loginmvcmaven.model.Role;
import org.academiadecodigo.loginmvcmaven.model.User;
import org.academiadecodigo.loginmvcmaven.model.dao.RoleDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateRoleDao extends HibernateDao<Role> implements RoleDao {

    public HibernateRoleDao() {
        super(Role.class);
    }

    @Override
    public Role findByName(String rolename) {
        try {
            Query query = HibernateSessionManager.getSession().createQuery("from Role where name = :name");

            query.setString("name", rolename);
            return (Role) query.uniqueResult();

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

}
