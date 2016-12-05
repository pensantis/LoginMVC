package org.academiadecodigo.loginmvcmaven.model.dao.hibernate;

import org.academiadecodigo.loginmvcmaven.Persistence.TransactionException;
import org.academiadecodigo.loginmvcmaven.Persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.loginmvcmaven.model.User;
import org.academiadecodigo.loginmvcmaven.model.dao.UserDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateUserDao implements UserDao {
    @Override
    public void save(User user) {
        try {
            HibernateSessionManager.getSession().save(user);
        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public void delete(User user) {
        try {
            HibernateSessionManager.getSession().delete(user);
        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public User findByName(String username) {
        try {
            Query query = HibernateSessionManager.getSession().createQuery("from User where username = :username");

            query.setString("username", username);
            return (User) query.uniqueResult();

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public long count() {
        try {
            return HibernateSessionManager.getSession()
                    .createQuery("select count (*) from " + User.class.getSimpleName())
                    .list().size();
        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public boolean userExist(String name) {
        boolean result = false;
        try {
           // Session session = HibernateSessionManager.beginTransaction();

            Query query = HibernateSessionManager.getSession().createQuery("FROM User where username = :username");
            query.setString("username", name);

            if (query.uniqueResult() == null){
                System.out.println("User doesn't exist");
                result = false;
            }else {
                System.out.println("User Exist");
                result = true;
            }

          //  HibernateSessionManager.commitTransaction();
        }catch (HibernateException ex){
            ex.printStackTrace();
            throw new TransactionException(ex);
        }
        return result;
    }
}
