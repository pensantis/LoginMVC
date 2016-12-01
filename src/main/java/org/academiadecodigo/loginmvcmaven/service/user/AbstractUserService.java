package org.academiadecodigo.loginmvcmaven.service.user;

/**
 * Created by codecadet on 28/11/16.
 */
public abstract class AbstractUserService implements UserService {
    @Override
    public String getServiceName() {
        return UserService.class.getName();
    }
}
