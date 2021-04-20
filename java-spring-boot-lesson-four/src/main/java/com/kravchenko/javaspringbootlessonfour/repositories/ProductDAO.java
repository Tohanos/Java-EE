package com.kravchenko.javaspringbootlessonfour.repositories;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductDAO {

    Session session;

    @PostConstruct
    public void init() {

        add(new Product(null, "MacBook", "Ultra low and Great Power", new BigDecimal(3000)));
        add(new Product(null, "iPhone", "The most expensive phone by credit", new BigDecimal(1000)));
        add(new Product(null, "iPad", "More size - more cost", new BigDecimal(2000)));

    }

    public void add(Product product) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void update(Product product) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Product product1 = (Product) session.get(Product.class, product.getId());
            product1.setName(product.getName());
            product1.setDescription(product.getDescription());
            product1.setPrice(product.getPrice());
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void remove(long id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Product product = (Product) session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public Product findById(long id) {

        Product product;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            product = (Product) session.get(Product.class, id);
//            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return product;
    }

    public List<Product> findAll() {

        List<Product> productList = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            productList = session.createQuery("from Product").list();

            session.getTransaction().commit();
        } finally {
            session.close();
        }

        return productList;
    }

}