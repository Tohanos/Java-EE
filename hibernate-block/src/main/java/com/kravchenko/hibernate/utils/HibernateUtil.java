package com.kravchenko.hibernate.utils;

import com.kravchenko.hibernate.entities.Customer;
import com.kravchenko.hibernate.entities.Manufacturer;
import com.kravchenko.hibernate.entities.Product;
import com.kravchenko.hibernate.entities.Purchase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Manufacturer.class)
                    .addAnnotatedClass(Purchase.class)
                .buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if(sessionFactory != null) {
            sessionFactory.close();
        }
    }
}