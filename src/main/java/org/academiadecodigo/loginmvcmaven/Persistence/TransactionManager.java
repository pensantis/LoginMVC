package org.academiadecodigo.loginmvcmaven.Persistence;

/**
 * Created by codecadet on 01/12/16.
 */
public interface TransactionManager {
    public void begin();

    public void commit();

    public void rollback();
}
