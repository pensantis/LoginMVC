package org.academiadecodigo.loginmvcmaven.Persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by codecadet on 29/11/16.
 */
public class HibernateSessionManager {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Hold services needed by Hibernate
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml") // Load settings from org.academiadecodigo.loginmvcmaven.Persistence.hibernate.cfg.xml
                    .build();

            sessionFactory = new MetadataSources(serviceRegistry)
                    .buildMetadata() // Tell Hibernate about sources of metadata (database mappings)
                    .buildSessionFactory();

        } catch (HibernateException ex) {
            throw new ExceptionInInitializerError("Error creating org.academiadecodigo.loginmvcmaven.Persistence.hibernate session factory: " + ex.getMessage());
        }
    }

    public static Session getSession() {
        // Hibernate will automatically open a new session if needed
        // Closing the session is not required
        return sessionFactory.getCurrentSession();
    }

    // Required to stop org.academiadecodigo.loginmvcmaven.Persistence.hibernate and allow the application to terminate
    public static void close() {
        sessionFactory.close();
    }

    public static Session beginTransaction() {
        Session session = getSession();
        session.beginTransaction();
        return session;
    }

    public static void commitTransaction() {
        getSession().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }
}
