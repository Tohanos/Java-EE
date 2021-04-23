package com.kravchenko.hibernate.repositories;

import com.kravchenko.hibernate.entities.Purchase;
import com.kravchenko.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseDAO {
    Session session;

    @Autowired
    public PurchaseDAO() {
    }

    public void add(Purchase purchase) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(purchase);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void update(Purchase purchase) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Purchase purchase1 = (Purchase) session.get(Purchase.class, purchase.getId());
            purchase1.setCustomer_id(purchase.getCustomer_id());
            purchase1.setProduct_id(purchase.getProduct_id());
            purchase1.setPrice(purchase.getPrice());
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void remove(long id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Purchase purchase = (Purchase) session.get(Purchase.class, id);
            session.delete(purchase);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public Purchase findById(long id) {

        Purchase purchase;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            purchase = (Purchase) session.get(Purchase.class, id);
        } finally {
            session.close();
        }
        return purchase;
    }

    public List<Purchase> findByCustomer(Long customerId) {
        List<Purchase> purchases;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            purchases = session.createQuery("from purchases p where p.customer_id = " + customerId).list();
        } finally {
            session.close();
        }
        return purchases;
    }

    public List<Purchase> findByProduct(Long productId) {
        List<Purchase> purchases;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            purchases = session.createQuery("from purchases p where p.product_id = '" + productId + "'").list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return purchases;
    }

    public List<Purchase> findAll() {

        List<Purchase> purchases = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            purchases = session.createQuery("from purchases").list();

            session.getTransaction().commit();
        } finally {
            session.close();
        }

        return purchases;
    }

}
