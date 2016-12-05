package org.academiadecodigo.loginmvcmaven.service.user;

import org.academiadecodigo.loginmvcmaven.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codecadet on 15/11/16.
 */
public class MockUserService extends AbstractUserService {
    private List<User> users = new ArrayList<User>();

    @Override
    public boolean authenticate(String name, String password) {
        if(users.size() == 0){
            return false;
        }
        for (User user:users) {
            if(user.getUsername().equals(name)){
                if (user.getPassword().equals(password)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User findByName(String name) {
        for (User user: users){
            if(user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }

    @Override
    public long count() {
        return users.size();
    }


    public List<User> getUsers() {
        return users;
    }

    public boolean userExist(String name){
        for (User user:users) {
            if(user.getUsername().equals(name)){
                return true;
            }
        }
        return false;
    }
}
