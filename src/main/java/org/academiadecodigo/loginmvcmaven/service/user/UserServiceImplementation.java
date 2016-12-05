package org.academiadecodigo.loginmvcmaven.service.user;

import org.academiadecodigo.loginmvcmaven.Persistence.TransactionException;
import org.academiadecodigo.loginmvcmaven.Persistence.TransactionManager;
import org.academiadecodigo.loginmvcmaven.Persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.loginmvcmaven.model.User;
import org.academiadecodigo.loginmvcmaven.model.dao.RoleDao;
import org.academiadecodigo.loginmvcmaven.model.dao.UserDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by codecadet on 01/12/16.
 */
public class UserServiceImplementation implements UserService {
    UserDao userDao;
    RoleDao roleDao;
    TransactionManager transactionManager;

    public UserServiceImplementation(UserDao userDao, RoleDao roleDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.transactionManager = transactionManager;
    }



    @Override
    public boolean authenticate(String name, String password) {
        boolean result = false;

        try {
            transactionManager.begin();

            User user = userDao.findByName(name);
            result = (user != null && user.getPassword().equals(password));

            transactionManager.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            transactionManager.rollback();
        }

        return result;
    }

    @Override
    public void addUser(User user) {
        try {
            transactionManager.begin();

            if (userDao.findByName(user.getUsername()) == null) {
                userDao.save(user);
            }

            transactionManager.commit();
        } catch (TransactionException ex) {
            System.out.println(ex.getMessage());
            transactionManager.rollback();

        }
    }

    @Override
    public User findByName(String name) {
        User user = null;
        try {
            transactionManager.begin();

            user = userDao.findByName(name);

            transactionManager.commit();

        } catch (TransactionException ex) {
            System.out.println(ex.getMessage());
            transactionManager.rollback();
        }

        return user;
    }

    @Override
    public long count() {
        long size = 0;
        try {
            transactionManager.begin();
            size = userDao.count();
            transactionManager.commit();
        } catch (TransactionException ex) {
            System.out.println(ex.getMessage());
            transactionManager.rollback();
        }

        return size;
    }

    @Override
    public boolean userExist(String name) {
        boolean result = false;
        try {
            transactionManager.begin();

            result = userDao.userExist(name);

            transactionManager.commit();
        }catch (TransactionException ex){
            System.out.println(ex.getMessage());
            transactionManager.rollback();
        }
        return result;
    }

    @Override
    public String getServiceName() {
        return UserService.class.getName();
    }
}
