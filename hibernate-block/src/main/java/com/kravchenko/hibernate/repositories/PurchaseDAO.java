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
            purchase1.setCustomer(purchase.getCustomer());
            purchase1.setProduct(purchase.getProduct());
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
