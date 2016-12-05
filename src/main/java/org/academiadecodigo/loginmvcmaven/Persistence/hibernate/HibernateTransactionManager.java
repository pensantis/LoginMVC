package org.academiadecodigo.loginmvcmaven.Persistence.hibernate;

import org.academiadecodigo.loginmvcmaven.Persistence.TransactionManager;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateTransactionManager implements TransactionManager {
    @Override
    public void begin() {
        HibernateSessionManager.beginTransaction();
    }

    @Override
    public void commit() {
        HibernateSessionManager.commitTransaction();
    }

    @Override
    public void rollback() {
        HibernateSessionManager.rollbackTransaction();
    }
}
