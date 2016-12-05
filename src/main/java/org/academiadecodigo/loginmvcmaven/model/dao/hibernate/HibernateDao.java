package org.academiadecodigo.loginmvcmaven.model.dao.hibernate;

import org.academiadecodigo.loginmvcmaven.Persistence.TransactionException;
import org.academiadecodigo.loginmvcmaven.Persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.loginmvcmaven.model.dao.Dao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;

import java.util.List;

/**
 * Created by codecadet on 05/12/16.
 */
public abstract class HibernateDao<T> implements Dao<T> {


    private Class<T> type;

    public HibernateDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public void save(T dao) {
        try {
            HibernateSessionManager.getSession().save(dao);
        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public void delete(T dao) {
        try {
            HibernateSessionManager.getSession().delete(dao);
        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }


    @Override
    public long count() {
        return (long) HibernateSessionManager.getSession().createCriteria(type)
                .setProjection(Projections.rowCount()).uniqueResult();

    }

    @Override
    public List<T> findAll() {
        return null;
    }
}
