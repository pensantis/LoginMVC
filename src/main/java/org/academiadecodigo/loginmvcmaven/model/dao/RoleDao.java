package org.academiadecodigo.loginmvcmaven.model.dao;

import org.academiadecodigo.loginmvcmaven.model.Role;

/**
 * Created by codecadet on 01/12/16.
 */
public interface RoleDao {
    void save(Role role);

    void delete(Role role);

    Role findByName(String rolename);

    long count();
}
