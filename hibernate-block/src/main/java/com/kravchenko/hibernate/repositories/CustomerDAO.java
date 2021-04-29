package com.kravchenko.hibernate.repositories;

import com.kravchenko.hibernate.entities.Customer;
import com.kravchenko.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAO {

    Session session;

    @Autowired
    public CustomerDAO() {
    }

    public void add(Customer customer) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void update(Customer customer) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Customer customer1 = (Customer) session.get(Customer.class, customer.getId());
            customer1.setName(customer.getName());

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void remove(long id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Customer customer = (Customer) session.get(Customer.class, id);
            session.delete(customer);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public Customer findById(long id) {

        Customer customer;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            customer = (Customer) session.get(Customer.class, id);

        } finally {
            session.close();
        }
        return customer;
    }

    public List<Customer> findAll() {

        List<Customer> customers = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            customers = session.createQuery("from Customer").list();

            session.getTransaction().commit();
        } finally {
            session.close();
        }

        return customers;
    }
}
