package com.kravchenko.hibernate.repositories;

import com.kravchenko.hibernate.entities.Manufacturer;
import com.kravchenko.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManufacturerDAO {

    Session session;

    @Autowired
    public ManufacturerDAO() {
    }

    public void add(Manufacturer manufacturer) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(manufacturer);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void update(Manufacturer manufacturer) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Manufacturer manufacturer1 = (Manufacturer) session.get(Manufacturer.class, manufacturer.getId());
            manufacturer1.setTitle(manufacturer.getTitle());

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void remove(long id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Manufacturer manufacturer = (Manufacturer) session.get(Manufacturer.class, id);
            session.delete(manufacturer);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public Manufacturer findById(long id) {

        Manufacturer manufacturer;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            manufacturer = (Manufacturer) session.get(Manufacturer.class, id);

        } finally {
            session.close();
        }
        return manufacturer;
    }

    public List<Manufacturer> findAll() {

        List<Manufacturer> manufacturers = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            manufacturers = session.createQuery("from Manufacturer").list();

            session.getTransaction().commit();
        } finally {
            session.close();
        }

        return manufacturers;
    }
}
