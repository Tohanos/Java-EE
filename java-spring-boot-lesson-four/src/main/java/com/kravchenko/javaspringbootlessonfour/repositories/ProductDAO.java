package com.kravchenko.javaspringbootlessonfour.repositories;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductDAO {

    @PostConstruct
    public void init() {
            add(new Product(null, "MacBook", "Ultra low and Great Power", new BigDecimal(3000)));
            add(new Product(null, "iPhone", "The most expensive phone by credit", new BigDecimal(1000)));
            add(new Product(null, "iPad", "More size - more cost", new BigDecimal(2000)));

    }

    public void add(Product product) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }

    public void update(Product product) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Product product1 = (Product) session.get(Product.class, product.getId());
            product1.setName(product.getName());
            product1.setDescription(product.getDescription());
            product1.setPrice(product.getPrice());
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }

    public void remove(long id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Product product = (Product) session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }

    public Product findById(long id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = null;

        Product product;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            product = (Product) session.get(Product.class, id);
//            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
        return product;
    }

    public List<Product> findAll() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        List<Product> productList = null;

        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            productList = session.createQuery("from Product").list();

            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }

        return productList;
    }
}