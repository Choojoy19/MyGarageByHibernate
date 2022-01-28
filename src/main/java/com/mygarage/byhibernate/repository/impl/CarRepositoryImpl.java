package com.mygarage.byhibernate.repository.impl;

import com.mygarage.byhibernate.config.ConfigSessionFactory;
import com.mygarage.byhibernate.model.Car;
import com.mygarage.byhibernate.repository.BaseRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public class CarRepositoryImpl implements BaseRepository<Car> {
    @Override
    public List<Car> findAll() {
        Session session = ConfigSessionFactory.getSessionFactory().openSession();

        return (List<Car>) session.createQuery("from Car").getResultList();
    }

    @Override
    public Car findById(long id) {
        Session session = ConfigSessionFactory.getSessionFactory().openSession();
        return session.get(Car.class, id);
    }



    @Override
    public boolean create(Car entity) {
        Transaction transaction = null;
        try (Session session = ConfigSessionFactory.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public Car update(Car entity) {
        Transaction transaction = null;
        try (Session session = ConfigSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return session.get(Car.class, entity.getId());
        } catch (Exception exc) {
            if (transaction != null) {
                transaction.rollback();
            }

        }
        return entity;
    }

    @Override
    public boolean deleteById(long id) {
        Transaction transaction = null;
        try (Session session = ConfigSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Car car = session.get(Car.class, id);
            if (car != null) {
                session.delete(car);
                transaction.commit();
                //session.close();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return false;
    }

}


