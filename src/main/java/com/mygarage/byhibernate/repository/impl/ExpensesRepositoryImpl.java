package com.mygarage.byhibernate.repository.impl;

import com.mygarage.byhibernate.config.ConfigSessionFactory;
import com.mygarage.byhibernate.model.Car;
import com.mygarage.byhibernate.model.Expenses;
import com.mygarage.byhibernate.repository.BaseRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ExpensesRepositoryImpl implements BaseRepository<Expenses> {
    @Override
    public List<Expenses> findAll() {
        Session session = ConfigSessionFactory.getSessionFactory().openSession();

        return (List<Expenses>) session.createQuery("from Expenses").getResultList();
    }

    @Override
    public Expenses findById(long id) {
        Session session = ConfigSessionFactory.getSessionFactory().openSession();
        return session.get(Expenses.class, id);
    }



    @Override
    public boolean create(Expenses entity) {
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
    public Expenses update(Expenses entity) {
        Transaction transaction = null;
        try (Session session = ConfigSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return session.get(Expenses.class, entity.getId());
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
            Expenses expense = session.get(Expenses.class, id);
            if (expense != null) {
                session.delete(expense);
                transaction.commit();
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
