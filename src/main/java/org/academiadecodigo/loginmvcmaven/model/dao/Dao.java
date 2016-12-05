package org.academiadecodigo.loginmvcmaven.model.dao;

import java.util.List;

/**
 * Created by codecadet on 05/12/16.
 */
public interface Dao<T> {
    void save(T dao);

    void delete(T dao);

    /*T findByName(String name);*/

    List<T> findAll();

    long count();
}
