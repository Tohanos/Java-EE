package com.kravchenko.hibernate.repositories;

import com.kravchenko.hibernate.entities.Product;
import com.kravchenko.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {
    Session session;

    @Autowired
    public ProductDAO() {
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
            product1.setTitle(product.getTitle());
            product1.setManufacturer(product.getManufacturer());
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

        } finally {
//            session.close();
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

