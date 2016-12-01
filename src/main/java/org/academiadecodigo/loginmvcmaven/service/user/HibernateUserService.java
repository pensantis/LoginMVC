package org.academiadecodigo.loginmvcmaven.service.user;

import org.academiadecodigo.loginmvcmaven.Persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.loginmvcmaven.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateUserService extends AbstractUserService {
    @Override
    public boolean authenticate(String name, String password) {
        boolean result = false;
        try {
            Session session = HibernateSessionManager.beginTransaction();

            //Criteria criteria = session.createCriteria(User.class);

            //System.out.println(criteria.add(Restrictions.eq("username", name)).add(Restrictions.eq("password", password)));
           /* User user = (User)criteria.uniqueResult();
            System.out.println(user.getUsername());*/
            Query query = session.createQuery("FROM User where username = :username and password = :password");
            query.setString("username", name);
            query.setString("password", password);

            if((User)query.uniqueResult() == null){
                result = false;
            }else {
                result = true;
            }


            HibernateSessionManager.commitTransaction();

        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateSessionManager.rollbackTransaction();
        }
        return result;
    }

    @Override
    public void addUser(User user) {
        try {
            Session session = HibernateSessionManager.beginTransaction();

            session.save(user);


            HibernateSessionManager.commitTransaction();
        }catch (HibernateException ex){
            ex.printStackTrace();
            System.out.println("user exist");
            HibernateSessionManager.rollbackTransaction();
        }

    }

    @Override
    public User findByName(String name) {
        User user = null;
        try {
            Session session = HibernateSessionManager.beginTransaction();

            Query query = session.createQuery("FROM User where username = :username");
            query.setString("username", name);

            if (query.uniqueResult() == null){
                System.out.println("User don't exist");

            }else {
                System.out.println("User Exist");
                user = (User)query.uniqueResult();
            }

            System.out.println(user);

            HibernateSessionManager.commitTransaction();
        }catch (HibernateException ex){
            ex.printStackTrace();
            HibernateSessionManager.rollbackTransaction();
        }
        return user;
    }

    @Override
    public int count() {
        int result = 0;
        try {
            Session session = HibernateSessionManager.beginTransaction();

            Query query = session.createQuery("select count (*) FROM User");

            System.out.println(query.uniqueResult());

            if (((Long)query.uniqueResult()).intValue() == 0){

                result = 0;
            }else {
                System.out.println("Outro numero");
                result = ((Long)query.uniqueResult()).intValue();
            }


            HibernateSessionManager.commitTransaction();
        }catch (HibernateException ex){
            ex.printStackTrace();
            HibernateSessionManager.rollbackTransaction();
        }
        return result;
    }


    @Override
    public boolean userExist(String name) {
        boolean result = false;
        try {
            Session session = HibernateSessionManager.beginTransaction();

            Query query = session.createQuery("FROM User where username = :username");
            query.setString("username", name);

            if (query.uniqueResult() == null){
                System.out.println("User don't exist");
                result = false;
            }else {
                System.out.println("User Exist");
                result = true;
            }

            HibernateSessionManager.commitTransaction();
        }catch (HibernateException ex){
            ex.printStackTrace();
            HibernateSessionManager.rollbackTransaction();
        }
        return result;
    }
}
