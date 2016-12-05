package org.academiadecodigo.loginmvcmaven.model.dao;

import org.academiadecodigo.loginmvcmaven.model.Role;

/**
 * Created by codecadet on 01/12/16.
 */
public interface RoleDao  extends Dao<Role>{

    Role findByName(String rolename);


}
