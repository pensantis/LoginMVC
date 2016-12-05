package org.academiadecodigo.loginmvcmaven.model.dao;

import org.academiadecodigo.loginmvcmaven.model.User;

/**
 * Created by codecadet on 01/12/16.
 */
public interface UserDao {
    void save(User user);

    void delete(User user);

    User findByName(String username);

    long count();

    boolean userExist(String name);
}
