package com.mygarage.byhibernate.service.impl;

import com.mygarage.byhibernate.model.Expenses;
import com.mygarage.byhibernate.repository.ExpensesRepository;
import com.mygarage.byhibernate.repository.impl.ExpensesRepositoryImpl;
import com.mygarage.byhibernate.service.ExpensesService;

import java.time.LocalDate;
import java.util.Set;

public class ExpensesServiceImpl implements ExpensesService {
    private final ExpensesRepository repository = new ExpensesRepositoryImpl();
    @Override
    public Set<Expenses> findAll() {
        return repository.findAll();
    }

    @Override
    public Expenses findById(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean create(Expenses entity) {
        return repository.create(entity);
    }

    @Override
    public boolean deleteById(long id) {
        return repository.deleteById(id);
    }
    @Override
    public Expenses update (Expenses entity){
        return repository.update(entity);
    }

    @Override
    public String sumExpense(LocalDate fromDate, LocalDate toDate, String id, String typeOfExpense) {
        return repository.sumExpense(fromDate, toDate, id, typeOfExpense);
    }
}
