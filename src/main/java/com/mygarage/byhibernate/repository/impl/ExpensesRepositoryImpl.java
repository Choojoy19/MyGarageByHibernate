package com.mygarage.byhibernate.repository.impl;

import com.mygarage.byhibernate.config.ConfigSessionFactory;
import com.mygarage.byhibernate.model.Expenses;
import com.mygarage.byhibernate.repository.ExpensesRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ExpensesRepositoryImpl implements ExpensesRepository {



    @Override
    public Set<Expenses> findAll() {
        Session session = ConfigSessionFactory.getSessionFactory().openSession();

        return (Set<Expenses>) session.createQuery("from Expenses").getResultList();
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

    @Override
    public String sumExpense(LocalDate fromDate, LocalDate toDate, String id, String typeOfExpense) {
        Session session = ConfigSessionFactory.getSessionFactory().openSession();
        if (typeOfExpense.equals("allexpenses")){
            String hqlSum = "select sum (e.price) from Car c inner join c.expenses as e where c.id =:id and (e.date) between :fromDate and :toDate";
            Long result = (Long) session.createQuery(hqlSum).setString("id",id)
                    .setParameter("fromDate", fromDate)
                    .setParameter("toDate", toDate).list().get(0);
            return String.valueOf(result);
        } else {
            String hqlSum = "select sum (e.price) from Car c inner join c.expenses as e where c.id =:id and (e.date) between :fromDate and :toDate and (e.typeOfExpense) =: type";
            Long result = (Long) session.createQuery(hqlSum).setString("id",id).setParameter("fromDate", fromDate).setParameter("toDate", toDate).setString("type", typeOfExpense).list().get(0);
             return String.valueOf(result);
        }

    }
}
