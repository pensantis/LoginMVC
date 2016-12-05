package org.academiadecodigo.loginmvcmaven.service.user;

import org.academiadecodigo.loginmvcmaven.model.User;
import org.academiadecodigo.loginmvcmaven.service.Service;

/**
 * Created by codecadet on 15/11/16.
 */
public interface UserService extends Service{
    boolean authenticate(String name, String password);
    void addUser(User user);
    User findByName(String name);
    long count();
    public boolean userExist(String name);
}


