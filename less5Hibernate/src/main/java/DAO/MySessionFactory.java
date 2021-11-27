package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {

    private static volatile SessionFactory sessionFactory;

    public MySessionFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            synchronized (MySessionFactory.class){
                if (sessionFactory == null){
                    sessionFactory = new Configuration()
                            .configure("hibernate.org.xml")
                            .buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }
}
