package org.academiadecodigo.loginmvcmaven.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by codecadet on 01/12/16.
 */
public class Role {
    Integer id;
    String name;
    private Set<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
