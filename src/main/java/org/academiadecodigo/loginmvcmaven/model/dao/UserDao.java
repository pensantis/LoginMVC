package org.academiadecodigo.loginmvcmaven.model.dao;

import org.academiadecodigo.loginmvcmaven.model.User;

/**
 * Created by codecadet on 01/12/16.
 */
public interface UserDao extends Dao<User>{

    User findByName(String username);

    boolean userExist(String name);

}
