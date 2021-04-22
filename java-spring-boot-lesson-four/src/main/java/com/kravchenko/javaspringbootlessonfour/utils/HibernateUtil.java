package com.kravchenko.javaspringbootlessonfour.utils;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }
    public static synchronized SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        }
        return sessionFactory;
    }
}